package com.tramexmeters.mex5update.features.scan.browser.views

import android.bluetooth.BluetoothGattDescriptor
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.tramexmeters.mex5update.bluetooth.parsing.Engine
import com.tramexmeters.mex5update.R
import com.tramexmeters.mex5update.databinding.DescriptorContainerBinding
import com.tramexmeters.mex5update.utils.UuidUtils

class DescriptorContainer(
        context: Context,
        private val callback: Callback,
        val descriptor: BluetoothGattDescriptor
) : LinearLayout(context) {

    private val _binding: DescriptorContainerBinding =
            DescriptorContainerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initViews()
        setupUiListeners()
    }

    private fun initViews() {
        _binding.apply {
            tvName.text = Engine.getDescriptorByUUID(descriptor.uuid)?.name
                    ?: context.getString(R.string.unknown_descriptor_label)
            tvUuid.text = UuidUtils.getUuidText(descriptor.uuid)
        }
    }

    private fun setupUiListeners() {
        _binding.tvRead.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(
                    context,
                    R.anim.property_image_click
            ))
            callback.onReadPropertyClicked(descriptor)
        }
    }

    interface Callback {
        fun onReadPropertyClicked(descriptor: BluetoothGattDescriptor)
    }
}