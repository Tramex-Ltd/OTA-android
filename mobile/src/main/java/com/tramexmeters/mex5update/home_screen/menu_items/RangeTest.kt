package com.tramexmeters.mex5update.home_screen.menu_items

import com.tramexmeters.mex5update.bluetooth.services.BluetoothService

class RangeTest(imageResId: Int, title: String, description: String) : DemoMenuItem(imageResId, title, description) {

    override val connectType = BluetoothService.GattConnectType.RANGE_TEST
}