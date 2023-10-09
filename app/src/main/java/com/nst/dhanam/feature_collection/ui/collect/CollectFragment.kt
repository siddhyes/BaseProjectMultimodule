package com.nst.dhanam.feature_collection.ui.collect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.nst.dhanam.R
import com.nst.dhanam.common.ui.BindingFragment
import com.nst.dhanam.databinding.FragmentCollectBinding
import com.nst.dhanam.feature_collection.ui.dashboard.GraphMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectFragment() : BindingFragment<FragmentCollectBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentCollectBinding::inflate
    private val graphViewModel: GraphMainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textvie.setOnClickListener {
            findNavController().navigate(R.id.action_collect_to_groupScheduledList)
        }
    }

}
