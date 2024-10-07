package com.tramexmeters.mex5updater.home_screen.menu_items

import com.tramexmeters.mex5updater.bluetooth.services.BluetoothService

class ConnectedLighting(imageResId: Int, title: String, description: String) : DemoMenuItem(imageResId, title, description) {

    override val connectType = BluetoothService.GattConnectType.LIGHT
}