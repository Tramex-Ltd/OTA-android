package com.tramexmeters.mex5updater.bluetooth.ble

class ConnectedDeviceInfo(val connection: GattConnection) {
    var bluetoothInfo = BluetoothDeviceInfo(connection.gatt!!.device)
}