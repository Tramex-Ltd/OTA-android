package com.tramexmeters.mex5update.features.scan.browser.models

class Mapping(
        var uuid: String,
        var name: String
) {
    enum class Type {
        SERVICE,
        CHARACTERISTIC
    }
}
