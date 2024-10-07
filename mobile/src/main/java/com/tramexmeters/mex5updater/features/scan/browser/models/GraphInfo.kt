package com.tramexmeters.mex5updater.features.scan.browser.models

import com.tramexmeters.mex5updater.features.scan.rssi_graph.model.GraphPoint

data class GraphInfo(
        val data: MutableList<GraphPoint> = mutableListOf(),
        val dataColor: Int
)