package com.tramexmeters.mex5updater.features.scan.active_connections.adapters

import android.bluetooth.BluetoothDevice


interface ConnectionsAdapterCallback {
    fun onDisconnectClicked(deviceAddress: String)
    fun onDeviceClicked(deviceToConnect: BluetoothDevice)
}
