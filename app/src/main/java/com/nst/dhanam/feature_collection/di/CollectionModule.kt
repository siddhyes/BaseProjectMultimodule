package com.nst.dhanam.feature_collection.di

import com.nst.dhanam.feature_collection.ui.dashboard.GraphMainViewModel
import com.nst.feature_auth.ui.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CollectionModule{
    val viewModelCollection = module {
        viewModel { GraphMainViewModel() }
        viewModel { AuthViewModel() }
    }
}