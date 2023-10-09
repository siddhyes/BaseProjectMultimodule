package com.nst.dhanam.common.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.nst.dhanam.common.data.PreferencesManager
import com.nst.dhanam.feature_collection.di.CollectionModule
import com.nst.feature_auth.di.FeatureAuthModule
import com.nst.module_navigation.Navigator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit = module {
    single {
        val clientBuilder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY

            })

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(ChuckerInterceptor(androidContext()))
            clientBuilder.addInterceptor(logging)
        }/* clientBuilder.addInterceptor(object : Interceptor {
             override fun intercept(chain: Interceptor.Chain): Response {
                 val request: Request = chain.request()
                 val response = chain.proceed(newRequestWithUserToken(request))
                 if (response.code == 401) {
                     MiFixApplication.tokenExpired()
                     return response
                 }
                 return response
             }
         })*/

//        val auth = sharedPreferences.getValueString( "AUTHORIZATION")?:""
//        if (auth.isNotEmpty()) {
//            builder.addInterceptor { chain ->
//                var request = chain.request()
//                val requestBuilder = request.newBuilder()
//                //.header(AppENUM.AUTHORIZATION, String.format("%s %s", "Bearer", auth))
//                // .header("Version", BuildConfig.VERSION_NAME)
//                Log.e("Bearer", auth)
//                request = requestBuilder.build()
//                chain.proceed(request)
//            }
//        }
        clientBuilder.build()
    }
    single {
        Retrofit.Builder().baseUrl("https://mocki.io/v1/").client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
val navigation = module {
    single<Navigator.Provider> { DefaultNavigator() }
}

val dataStore = module {
    single { PreferencesManager(context = androidContext()) }
}

val viewModel = module {

}


fun getAll() = listOf(
    viewModel, dataStore, retrofit, FeatureAuthModule.viewModel, navigation,
    CollectionModule.viewModelCollection
)



