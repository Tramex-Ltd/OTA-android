package com.tramexmeters.mex5updater.home_screen.menu_items

import androidx.annotation.DrawableRes
import com.tramexmeters.mex5updater.bluetooth.services.BluetoothService

abstract class DemoMenuItem(@DrawableRes val imageResId: Int, val title: String, val description: String) {

    abstract val connectType: BluetoothService.GattConnectType
}