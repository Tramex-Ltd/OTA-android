package com.tramexmeters.mex5update.features.scan.browser.views

import android.bluetooth.BluetoothGattService
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import com.tramexmeters.mex5update.bluetooth.parsing.Common
import com.tramexmeters.mex5update.bluetooth.parsing.Engine
import com.tramexmeters.mex5update.features.scan.browser.models.Mapping
import com.tramexmeters.mex5update.R
import com.tramexmeters.mex5update.databinding.ServiceContainerBinding
import com.tramexmeters.mex5update.utils.UuidUtils
import java.util.*

class ServiceItemContainer(
        context: Context,
        private val callback: Callback,
        val service: BluetoothGattService,
        private val isMandatorySystemService: Boolean,
        private val serviceDictionaryUuids: Map<String, Mapping>
) : LinearLayout(context) {

    private var nameType = NameType.UNKNOWN
    private val _binding: ServiceContainerBinding = ServiceContainerBinding.inflate(LayoutInflater.from(context), this, true)

    companion object {
        private const val ANIMATION_DURATION_FOR_EXPAND_AND_COLLAPSE = 333L
    }

    init {
        initViews()
        setupUiListeners()
    }

    private fun initViews() {
        _binding.apply {
            serviceCharacteristicsContainer.apply {
                visibility = View.GONE
                removeAllViews()
            }

        }
    }

    private fun setupUiListeners() {

    }

    fun getServiceName() : String {
        return Engine.getService(service.uuid)?.let { serv ->
            nameType = NameType.ENGINE

            if (isMandatorySystemService) markAsSystemService(serv.name)
            else serv.name
        } ?: run {
            Common.getCustomServiceName(service.uuid, context)?.let { name ->
                nameType = NameType.CUSTOM
                name
            }
        } ?: run {
            serviceDictionaryUuids[UuidUtils.getUuidText(service.uuid)]?.let { mapping ->
                nameType = NameType.USER
                mapping.name
            }
        } ?: run {
            nameType = NameType.UNKNOWN
            context.getString(R.string.unknown_service)
        }
    }

    fun setServiceName(newName: String) {
        nameType = NameType.USER

    }

    private fun animateCharacteristicExpansion() {
        val container = _binding.serviceCharacteristicsContainer.apply {
            measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams?.height = 1 // smooths animation
            visibility = View.VISIBLE
        }
        val targetHeight = container.measuredHeight

        val animation: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                container.layoutParams?.height =
                        if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT
                        else (targetHeight * interpolatedTime).toInt()
                container.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        animation.duration = ANIMATION_DURATION_FOR_EXPAND_AND_COLLAPSE
        container.startAnimation(animation)
    }

    fun addCharacteristicContainer(container: CharacteristicItemContainer) {
        _binding.serviceCharacteristicsContainer.addView(container)
    }

    private fun markAsSystemService(name: String?) : String? {
        return name?.let {
            StringBuilder().apply {
                append(name)
                append(" (System)")
            }.toString()
        }
    }

    fun setMargins(position: Int) {
        val verticalSpacing = context.resources.getDimensionPixelSize(
                R.dimen.recycler_view_card_view_vertical_separation)
        val horizontalMargin = context.resources.getDimensionPixelSize(
                R.dimen.recycler_view_card_view_horizontal_margin)

        _binding.serviceInfoCardView.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ).apply {
            when (position) {
                0 -> setMargins(horizontalMargin, verticalSpacing, horizontalMargin, verticalSpacing)
                else -> setMargins(horizontalMargin, 0, horizontalMargin, verticalSpacing)
            }
        }
    }

    interface Callback {
        fun onRenameClicked(container: ServiceItemContainer)
    }

    enum class NameType {
        ENGINE,
        CUSTOM,
        USER,
        UNKNOWN
    }

}
