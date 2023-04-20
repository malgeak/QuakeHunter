package com.malgeak.quakehunter.ui.quakehunter.list

import com.malgeak.quakehunter.data.model.EarthQuake

interface OnItemEarthQuakeClickListener {

    fun onClickItem(earthQuake: EarthQuake)
}