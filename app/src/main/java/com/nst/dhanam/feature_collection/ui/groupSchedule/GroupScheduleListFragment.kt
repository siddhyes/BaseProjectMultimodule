package com.nst.dhanam.feature_collection.ui.groupSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.nst.dhanam.common.ui.BindingFragment
import com.nst.dhanam.databinding.FragmentGroupScheduledListBinding
import com.nst.dhanam.databinding.FragmentHistoryBinding
import com.nst.dhanam.feature_collection.ui.dashboard.GraphMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupScheduleListFragment() : BindingFragment<FragmentGroupScheduledListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentGroupScheduledListBinding::inflate
    /*  private val mainGraphViewModel: MainGraphViewModel by viewModel(ownerProducer = {
          findRootNavController().getBackStackEntry(R.id.mainFragment)
      })*/

    /* private val navController by lazy {
         requireNotNull(binding).nestedHomeNavigationHost.getFragment<NavHostFragment>().navController
     }*/

    /* private val onBackPressedCallback: OnBackPressedCallback =
         object : OnBackPressedCallback(true) {
             override fun handleOnBackPressed() {
                 navController.popBackStack()
             }
         }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {/* activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPressedCallback)
         addDestinationChangeListener()
 *//* mainGraphViewModel.toolbarBackEvent
             .flowWithLifecycle(viewLifecycleOwner.lifecycle)
             .onEach { navController.popBackStack() }
             .launchIn(viewLifecycleOwner.lifecycleScope)

         mainGraphViewModel.backToGraphRootEvent
             .flowWithLifecycle(viewLifecycleOwner.lifecycle)
             .onEach {
                 navController.popBackStack(
                     navController.graph.findStartDestination().id,
                     false
                 )
             }
             .launchIn(viewLifecycleOwner.lifecycleScope)*/
    }

    /*private fun addDestinationChangeListener() {
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    if (binding == null) {
                        controller.removeOnDestinationChangedListener(this)
                        return
                    }

                    destination.hierarchy.forEach {
                        submitToolbarTitle(it.id)
                        configureBackNavigation(it.id)
                    }
                }
            })
    }*/

    /*    private fun submitToolbarTitle(destinationId: Int) = when (destinationId) {
            R.id.tradingFragment -> R.string.home
            R.id.coinHistoryFragment -> R.string.coin_history
            R.id.homeFragmentChild2 -> R.string.home_child_2
            R.id.homeFragmentChild3 -> R.string.home_child_3
            R.id.coinDetailFragment -> R.string.coin_details
            else -> null
        }?.let(mainGraphViewModel::submitToolbarTitle)*/

    /* private fun configureBackNavigation(destinationId: Int) = when (destinationId) {
         navController.graph.findStartDestination().id -> {
             onBackPressedCallback.isEnabled = false
             mainGraphViewModel.showToolbarBackButton(false)
         }

         navController.graph.id -> Unit
         else -> {
             onBackPressedCallback.isEnabled = true
             mainGraphViewModel.showToolbarBackButton(true)
         }
     }*/
}
