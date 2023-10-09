package com.nst.feature_auth.di

import com.nst.feature_auth.ui.AuthViewModel
import org.koin.dsl.module

object FeatureAuthModule{

    val viewModel = module {
        single { AuthViewModel() }
    }
}