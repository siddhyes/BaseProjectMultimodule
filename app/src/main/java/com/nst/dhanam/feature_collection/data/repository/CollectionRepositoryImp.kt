package com.nst.dhanam.feature_collection.data.repository

import com.nst.feature_auth.data.local.AuthLocalDataSource
import com.nst.feature_auth.data.remote.AuthRemoteDataSource
import com.nst.feature_auth.domain.repository.AuthRepository

class CollectionRepositoryImp(
    remote: AuthRemoteDataSource, local: AuthLocalDataSource
) : AuthRepository {}