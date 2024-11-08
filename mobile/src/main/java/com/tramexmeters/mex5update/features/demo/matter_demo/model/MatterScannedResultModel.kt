package com.tramexmeters.mex5update.features.demo.matter_demo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatterScannedResultModel(
    var matterName: String,
    var matterAddress: String,
    var matterType: Int,
    var deviceId: Long,
    var deviceType: Int,
    var isDeviceOnline: Boolean=false,
 // val isDeleteIconVisible:Boolean
) : Parcelable {

/*    fun setDeleteIconVisible(b: Boolean) {

    }
    fun isDeleteIconVisible(): Boolean {
        return isDeleteIconVisible
    }*/

}




