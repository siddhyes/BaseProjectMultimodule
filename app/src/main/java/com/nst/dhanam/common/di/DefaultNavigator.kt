package com.nst.dhanam.common.di

import com.nst.dhanam.feature_collection.ui.dashboard.CollectionDashboardActivity
import com.nst.module_navigation.Activities
import com.nst.module_navigation.Navigator


class DefaultNavigator : Navigator.Provider {

    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.CollectionDashBoardActivity -> {
                CollectionDashboardActivity.GotoDashboardActivity
            }

        }
    }
}