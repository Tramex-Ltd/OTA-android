package com.tramexmeters.mex5updater.features.iop_test.models

data class ConnectionParameters(
        val mtu: Int,
        val pdu: Int,
        val interval: Double,
        val slaveLatency: Int,
        val supervisionTimeout: Int,
)
