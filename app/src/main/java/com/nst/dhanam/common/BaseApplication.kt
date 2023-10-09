package com.nst.dhanam.common

import android.app.Application
import coil.Coil
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.nst.dhanam.common.di.getAll
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setCoilImageLoader()
        initializeCoin()
    }

    private fun initializeCoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(getAll())
        }
    }


    private fun setCoilImageLoader() {
        val imageLoader = coil.ImageLoader.Builder(this@BaseApplication).memoryCache {
            MemoryCache.Builder(this).maxSizePercent(0.25).build()
        }.diskCache {
            DiskCache.Builder().directory(this.cacheDir.resolve("image_cache")).maxSizePercent(0.02)
                .build()
        }.components {
            add(SvgDecoder.Factory())
            add(ImageDecoderDecoder.Factory())
        }.build()
        Coil.setImageLoader(imageLoader)
    }

}