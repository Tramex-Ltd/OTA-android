package com.tramexmeters.mex5updater.home_screen.base

interface BluetoothDependent {

    fun onBluetoothStateChanged(isBluetoothOn: Boolean)
    fun onBluetoothPermissionsStateChanged(arePermissionsGranted: Boolean)
    fun refreshBluetoothDependentUi(isBluetoothOperationPossible: Boolean)
    fun setupBluetoothPermissionsBarButtons()
}