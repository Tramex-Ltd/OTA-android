package com.tramexmeters.mex5update.features.scan.browser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tramexmeters.mex5update.features.scan.browser.adapters.DictionaryEntryAdapter
import com.tramexmeters.mex5update.features.scan.browser.models.Mapping
import com.tramexmeters.mex5update.R
import com.tramexmeters.mex5update.common.other.CardViewListDecoration
import com.tramexmeters.mex5update.utils.SharedPrefUtils
import kotlinx.android.synthetic.main.fragment_service_mappings.*
import java.util.*
import kotlin.collections.ArrayList

class ServiceMappingsFragment : Fragment() {
    private lateinit var map: HashMap<String, Mapping>
    private lateinit var list: ArrayList<Mapping>
    private lateinit var sharedPrefUtils: SharedPrefUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefUtils = SharedPrefUtils(requireContext())
        map = sharedPrefUtils.serviceNamesMap
        list = ArrayList(map.values)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service_mappings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_services.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(CardViewListDecoration())
            adapter = DictionaryEntryAdapter(list, requireContext(), Mapping.Type.SERVICE)
        }
    }

    override fun onPause() {
        super.onPause()
        map.clear()
        for (mapping in list) map[mapping.uuid] = mapping
        sharedPrefUtils.saveServiceNamesMap(map)
    }
}
