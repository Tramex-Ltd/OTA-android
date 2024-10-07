package com.tramexmeters.mex5updater.features.scan.browser.models

import android.bluetooth.BluetoothDevice
import com.tramexmeters.mex5updater.bluetooth.ble.BluetoothDeviceInfo

data class ScannedDeviceInfo(
        var bluetoothInfo: BluetoothDeviceInfo,
        val graphInfo: GraphInfo,
        var isBluetoothInfoExpanded: Boolean = false
) {

    constructor(device: BluetoothDevice,
                isFavorite: Boolean,
                graphDataColor: Int
    ) : this(
            BluetoothDeviceInfo(device, isFavorite),
            GraphInfo(mutableListOf(), graphDataColor)
    )

}