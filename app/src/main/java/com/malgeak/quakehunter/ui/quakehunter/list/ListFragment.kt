package com.malgeak.quakehunter.ui.quakehunter.list

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.data.model.EarthQuake
import com.malgeak.quakehunter.databinding.FragmentListBinding
import com.malgeak.quakehunter.di.Injector
import com.malgeak.quakehunter.ui.base.BaseFragment
import javax.inject.Inject

class ListFragment : BaseFragment<ListViewModel>(), OnItemEarthQuakeClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListEarthQuakesAdapter

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as Injector).createListSubCoponent().inject(this)
        mViewModel = ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]
        mViewModel.setDateSelect(arguments?.getString("date"))
        if (arguments?.getBoolean("lastQuery", false)!!) {
            mViewModel.getLastRequest()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, null, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecyclerView()
        createObservers()
    }

    override fun onResume() {
        setTitle(getString(R.string.quake_title))
        super.onResume()
    }

    private fun createRecyclerView() {
        adapter = ListEarthQuakesAdapter(this)
        binding.recyclerViewContainer.adapter = adapter
        binding.recyclerViewContainer.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun createObservers() {
        mViewModel.listEarthQuake.observe(viewLifecycleOwner) {
            showLoader(false)
            if (it == null) {
                showDecisionDialog(getString(R.string.err_msg_no_data),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        binding.root.findNavController().popBackStack()
                    },
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        binding.root.findNavController().popBackStack()
                    })
            } else {
                adapter.setList(it)
            }
        }
    }

    override fun onClickItem(earthQuake: EarthQuake) {
        val stringEarthquake = Gson().toJson(earthQuake)
        val bundle = bundleOf("earthquake" to stringEarthquake)
        binding.root.findNavController()
            .navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

}