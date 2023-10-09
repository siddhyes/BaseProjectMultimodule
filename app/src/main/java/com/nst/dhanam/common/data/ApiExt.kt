package com.nst.dhanam.common.data

import com.google.gson.Gson
import com.nst.baseproject.common.data.remote.BaseResponse
import com.nst.baseproject.common.util.AppConstant
import com.nst.baseproject.common.util.Resource
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> executeApi(call: suspend () -> Response<BaseResponse<T>>): Resource<BaseResponse<T>> {
    return try {
        val response = call.invoke()

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                Resource.Success(body)
            } else {
                Resource.Error("Response body is null")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val message = if (!errorBody.isNullOrEmpty()) {
                try {
                    val errorResponse = Gson().fromJson(errorBody, BaseResponse::class.java)
                    errorResponse.message
                } catch (e: Exception) {
                    "Error parsing response"
                }
            } else {
                AppConstant.ERROR_UNKNOWN
            }
            Resource.Error(message)
        }
    } catch (e: IOException) {
        Resource.Error(AppConstant.ERROR_IO)
    } catch (e: Exception) {
        Resource.Error(e.toString())
    }
}