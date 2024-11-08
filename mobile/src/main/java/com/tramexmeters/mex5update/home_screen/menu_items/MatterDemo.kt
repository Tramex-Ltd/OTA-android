package com.tramexmeters.mex5update.home_screen.menu_items

import androidx.annotation.DrawableRes
import com.tramexmeters.mex5update.bluetooth.services.BluetoothService

class MatterDemo(
    @DrawableRes imageResId: Int,
    title: String,
    description: String
) : DemoMenuItem(imageResId, title, description) {
    override val connectType = BluetoothService.GattConnectType.MATTER_DEMO
}