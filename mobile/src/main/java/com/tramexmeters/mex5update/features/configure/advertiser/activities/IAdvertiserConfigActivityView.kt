package com.tramexmeters.mex5update.features.configure.advertiser.activities

import com.tramexmeters.mex5update.features.configure.advertiser.enums.AdvertisingMode
import com.tramexmeters.mex5update.features.configure.advertiser.enums.DataMode
import com.tramexmeters.mex5update.features.configure.advertiser.enums.Phy
import com.tramexmeters.mex5update.features.configure.advertiser.models.AdvertiserData
import com.tramexmeters.mex5update.features.configure.advertiser.models.DataPacket

interface IAdvertiserConfigActivityView {
    fun onAdvertisingTypesPrepared(isLegacy: Boolean, legacyModes: List<AdvertisingMode>, extendedModes: List<AdvertisingMode>)
    fun onAdvertisingParametersPrepared(isLegacy: Boolean, primaryPhys: List<Phy>, secondaryPhys: List<Phy>)
    fun onSupportedDataPrepared(isAdvertisingData: Boolean, isScanRespData: Boolean)
    fun onSaveHandled()
    fun populateUi(data: AdvertiserData, isAdvertisingEventSupported: Boolean)
    fun onDataLoaded(data: DataPacket?, mode: DataMode)
    fun updateAvailableBytes(advDataBytes: Int, scanRespDataBytes: Int, maxPacketSize: Int, includeFlags: Boolean)
}