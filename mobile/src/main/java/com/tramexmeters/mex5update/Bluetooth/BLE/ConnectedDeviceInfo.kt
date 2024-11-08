package com.tramexmeters.mex5update.bluetooth.ble

class ConnectedDeviceInfo(val connection: GattConnection) {
    var bluetoothInfo = BluetoothDeviceInfo(connection.gatt!!.device)
}