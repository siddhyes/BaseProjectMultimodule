package com.nst.feature_auth.data.repository

import com.nst.feature_auth.data.local.AuthLocalDataSource
import com.nst.feature_auth.data.remote.AuthRemoteDataSource
import com.nst.feature_auth.domain.repository.AuthRepository

class AuthRepositoryImp(
    remote: AuthRemoteDataSource, local: AuthLocalDataSource
) : AuthRepository {}