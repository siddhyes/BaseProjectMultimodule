package com.nst.dhanam.feature_collection.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.nst.baseproject.common.util.getDrawableResource
import com.nst.baseproject.common.util.hide
import com.nst.baseproject.common.util.visible
import com.nst.dhanam.R
import com.nst.dhanam.databinding.ActivityDashboardBinding
import com.nst.module_navigation.Navigator
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val graphViewModel: GraphMainViewModel by viewModel()

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, CollectionDashboardActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val navController = mainContainer.getFragment<NavHostFragment>().navController
            itemCollect.setOnClickListener {
                onBottomMenuItemClick(R.id.collectFragment, navController)
            }
            itemAdvance.setOnClickListener {
                onBottomMenuItemClick(R.id.advanceFragment, navController)
            }
            itemTicket.setOnClickListener {
                onBottomMenuItemClick(R.id.ticketFragment, navController)
            }
            itemHistory.setOnClickListener {
                onBottomMenuItemClick(R.id.historyFragment, navController)
            }
            addDestinationChangeListener(navController)
            addOnBackPressedCallback(navController)
        }
    }

    private fun onBottomMenuItemClick(destinationId: Int, navController: NavController) {
        if (navController.currentDestination?.id == destinationId) {
            graphViewModel.submitBackToGraphRootEvent()
        } else {
            navigateTo(destinationId, navController)
        }
    }

    private fun navigateTo(destinationId: Int, navController: NavController) {
        navController.navigate(destinationId, null, navOptions {
            launchSingleTop = true
            restoreState = false
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
        })
    }


    private fun addOnBackPressedCallback(navController: NavController) {
        onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                navController.run {
                    val startDestinationId = graph.findStartDestination().id
                    isEnabled = currentBackStackEntry?.destination?.id != startDestinationId
                    if (isEnabled) {
                        popBackStack(startDestinationId, false)
                    }
                }
            }
        })
    }

    private fun addDestinationChangeListener(navController: NavController) {
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController, destination: NavDestination, arguments: Bundle?
            ) {
                if (destination.id in listOf<Int>(
                        R.id.collectFragment,
                        R.id.historyFragment,
                        R.id.advanceFragment,
                        R.id.ticketFragment
                    )
                ) {
                    binding.apply {
                        bottomNavigationView.visible()
                        handleBottomNav(destination, binding)
                    } ?: navController.removeOnDestinationChangedListener(this)
                } else {
                    binding.bottomNavigationView.hide()
                }

            }

            private fun handleBottomNav(
                destination: NavDestination, binding: ActivityDashboardBinding
            ) {
                binding.apply {
                    itemCollect.setImageDrawable(getDrawableResource(R.drawable.ic_active_collect))
                    itemAdvance.setImageDrawable(getDrawableResource(R.drawable.ic_active_advance))
                    itemTicket.setImageDrawable(getDrawableResource(R.drawable.ic_in_active_ticket))
                    itemHistory.setImageDrawable(getDrawableResource(R.drawable.ic_in_active_history))
                    //analysisItem.setImageDrawable(iconAnalysisInactive)
                    destination.hierarchy.forEach {
                        when (it.id) {
                            R.id.collectFragment -> itemCollect.setImageDrawable(
                                getDrawableResource(R.drawable.ic_active_collect)
                            )

                            R.id.advanceFragment -> itemAdvance.setImageDrawable(
                                getDrawableResource(R.drawable.ic_active_advance)
                            )

                            R.id.ticketFragment -> itemTicket.setImageDrawable(
                                getDrawableResource(R.drawable.ic_in_active_ticket)
                            )

                            R.id.historyFragment -> itemHistory.setImageDrawable(
                                getDrawableResource(R.drawable.ic_in_active_history)
                            )

                        }
                    }
                }
            }
        })
    }

    object GotoDashboardActivity : Navigator {
        override fun navigate(activity: Activity) {
            CollectionDashboardActivity.launchActivity(activity)
        }
    }
}