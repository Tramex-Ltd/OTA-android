package com.tramexmeters.mex5update.features.scan.browser.adapters

import android.bluetooth.BluetoothDevice
import com.tramexmeters.mex5update.bluetooth.ble.BluetoothDeviceInfo

interface DebugModeCallback {
    fun connectToDevice(position: Int, deviceInfo: BluetoothDeviceInfo)
    fun disconnectDevice(position: Int, device: BluetoothDevice)
    fun addToFavorites(deviceAddress: String)
    fun removeFromFavorites(deviceAddress: String)
    fun toggleViewExpansion(position: Int)
}
