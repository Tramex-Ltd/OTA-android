package com.tramexmeters.mex5updater.bluetooth.ble.values

import android.bluetooth.BluetoothGattCharacteristic

interface ValueFactory<T> {
    fun create(value: BluetoothGattCharacteristic): T
}