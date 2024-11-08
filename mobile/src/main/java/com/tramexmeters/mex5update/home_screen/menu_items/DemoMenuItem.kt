package com.tramexmeters.mex5update.home_screen.menu_items

import androidx.annotation.DrawableRes
import com.tramexmeters.mex5update.bluetooth.services.BluetoothService

abstract class DemoMenuItem(@DrawableRes val imageResId: Int, val title: String, val description: String) {

    abstract val connectType: BluetoothService.GattConnectType
}