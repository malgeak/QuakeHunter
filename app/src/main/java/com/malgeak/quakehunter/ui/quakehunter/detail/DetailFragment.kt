package com.malgeak.quakehunter.ui.quakehunter.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.data.model.EarthQuake
import com.malgeak.quakehunter.databinding.FragmentDetailsBinding
import com.malgeak.quakehunter.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*


class DetailFragment : BaseFragment<DetailViewModel>(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: MapView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        mapFragment = binding.map
        mapFragment.onCreate(savedInstanceState)
        mapFragment.getMapAsync(this)

        arguments?.getString("earthquake", null)?.let { mViewModel.setEarthquake(it) }
        return binding.root
    }

    fun createObservers() {
        mViewModel.errorNoData.observe(viewLifecycleOwner) {
            if (it) {
                mViewModel.setAlertMessage(getString(R.string.err_msg_no_data))
                mViewModel.setErrorNull()
            }
        }

        mViewModel.earthQuake.observe(viewLifecycleOwner) {
            initView(it)
            mViewModel.title = it.properties.title ?: ""
            mViewModel.lat = it.geometry.coordinates[1]
            mViewModel.lon = it.geometry.coordinates[0]
        }

        mViewModel.mapReady.observe(viewLifecycleOwner) {
            if (it) {
                setMap(LatLng(mViewModel.lat, mViewModel.lon), mViewModel.title)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createObservers()
    }

    fun initView(earthQuake: EarthQuake) {
        val properties = earthQuake.properties
        val coordinates = earthQuake.geometry.coordinates
        val lon = coordinates.get(0)
        val lat = coordinates.get(1)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date = format.format(Date(earthQuake.properties.time))
        val lastUpdate = format.format(properties.updated?.let { Date(it) }) ?: ""
        val magnitude = earthQuake.properties.mag
        val scale = when (earthQuake.properties.magType) {
            "md" -> "de Duracion"
            else -> "Richter"
        }
        val place = earthQuake.properties.place ?: ""
        binding.textViewTitle.setText(properties.title)
        binding.textViewLatitude.setText(getString(R.string.latitud, lat))
        binding.textViewLongitud.setText(getString(R.string.longitud, lon))
        binding.textViewDate.setText(getString(R.string.date_quake, date))
        binding.textViewMagnitude.setText(getString(R.string.mag_quake, magnitude))
        binding.textViewPlace.setText(getString(R.string.place_quake, place))
        binding.textViewLastupdate.setText(getString(R.string.last_update, lastUpdate))
        binding.textViewDescription.setText(
            getString(
                R.string.quake_description,
                date,
                magnitude,
                scale,
                place
            )
        )
    }

    override fun onResume() {
        mapFragment.onResume()
        setTitle(mViewModel.title)
        super.onResume()
    }

    fun setMap(latLng: LatLng, title: String?) {
        if (googleMap != null) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(title)
            )

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12F));
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(8F), 2000, null);
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        try {
            MapsInitializer.initialize(requireActivity())
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
        googleMap = p0
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isZoomGesturesEnabled = false
        googleMap.uiSettings.isScrollGesturesEnabled = false
        googleMap.uiSettings.isRotateGesturesEnabled = false
        mViewModel.mapReady()
    }

    override fun onDestroy() {
        mapFragment.onDestroy()
        super.onDestroy()
    }

    override fun onPause() {
        mapFragment.onPause()
        super.onPause()
    }
}