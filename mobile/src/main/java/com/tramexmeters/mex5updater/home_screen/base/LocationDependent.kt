package com.tramexmeters.mex5updater.home_screen.base

interface LocationDependent {

    fun onLocationStateChanged(isLocationOn: Boolean)
    fun onLocationPermissionStateChanged(isPermissionGranted: Boolean)
    fun setupLocationBarButtons()
    fun setupLocationPermissionBarButtons()
}