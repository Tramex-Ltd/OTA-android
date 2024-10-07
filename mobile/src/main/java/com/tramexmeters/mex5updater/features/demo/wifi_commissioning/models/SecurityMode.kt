package com.tramexmeters.mex5updater.features.demo.wifi_commissioning.models

enum class SecurityMode(val value: Int) {
    OPEN(0),
    WPA(1),
    WPA2(2),
    WEP(3),
    EAP_WPA(4),
    EAP_WPA2(5),
    WPA_WPA2(6),
    WPA3(7),
    UNKNOWN(8);

    companion object {
        fun fromInt(code: Int) : SecurityMode {
            for (mode in values()) {
                if (mode.value == code) return mode
            }
            return UNKNOWN
        }
    }
}