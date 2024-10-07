package com.tramexmeters.mex5updater.features.scan.browser.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tramexmeters.mex5updater.base.fragments.BaseDialogFragment
import com.tramexmeters.mex5updater.R
import kotlinx.android.synthetic.main.dialog_ota_missing_characteristic.*

class OtaCharacteristicMissingDialog : BaseDialogFragment(
        hasCustomWidth = true,
        isCanceledOnTouchOutside = true
) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_ota_missing_characteristic, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_ok.setOnClickListener { dismiss() }
    }

}