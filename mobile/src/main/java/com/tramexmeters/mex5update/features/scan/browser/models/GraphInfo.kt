package com.tramexmeters.mex5update.features.scan.browser.models

import com.tramexmeters.mex5update.features.scan.rssi_graph.model.GraphPoint

data class GraphInfo(
        val data: MutableList<GraphPoint> = mutableListOf(),
        val dataColor: Int
)