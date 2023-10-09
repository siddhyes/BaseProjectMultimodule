package com.nst.baseproject.common.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.Transformation
import com.nst.dhanam.R
import java.io.File

object ImageLoader {


    fun ImageView.loadNetworkImage(
                  imageUrl: String?,
                  placeholderImage: Int? = R.drawable.ic_placeholder_doc,
                  errorImage: Int? = null,
                  defaultImage: Int = R.drawable.ic_placeholder_doc,
                  transform: Transformation? = null,
                  targetWidth: Int? = null,
                  targetHeight: Int? = null) {
        if (imageUrl.isNullOrEmpty()) {
            this.setImageResource(defaultImage)
        } else {
            this.load(imageUrl) {
                placeholderImage?.let {
                    placeholder(it)
                }

                errorImage?.let {
                    error(it)
                }

                transform?.let {
                    transformations(it)
                }

                if (targetHeight != null && targetWidth != null) {
                    size(targetHeight, targetWidth)
                }

                allowHardware(false)
            }
        }
    }

    fun ImageView.loadUriImage(
                  imageUrl: Uri?,
                  placeholderImage: Int? = R.drawable.ic_placeholder_doc,
                  errorImage: Int? = null,
                  defaultImage: Int = R.drawable.ic_placeholder_doc,
                  transform: Transformation? = null,
                  targetWidth: Int? = null,
                  targetHeight: Int? = null) {
        if (imageUrl == Uri.EMPTY) {
            this.setImageResource(defaultImage)
        } else {
            this.load(imageUrl) {
                placeholderImage?.let {
                    placeholder(it)
                }

                errorImage?.let {
                    error(it)
                }

                transform?.let {
                    transformations(it)
                }

                if (targetHeight != null && targetWidth != null) {
                    size(targetHeight, targetWidth)
                }

                allowHardware(false)
            }
        }
    }

    fun ImageView.loadBitmapImage(
                  imageUrl: Bitmap?,
                  placeholderImage: Int? = R.drawable.ic_placeholder_doc,
                  errorImage: Int? = null,
                  defaultImage: Int = R.drawable.ic_placeholder_doc,
                  transform: Transformation? = null,
                  targetWidth: Int? = null,
                  targetHeight: Int? = null) {

        this.load(imageUrl) {
            placeholderImage?.let {
                placeholder(it)
            }

            errorImage?.let {
                error(it)
            }

            transform?.let {
                transformations(it)
            }

            if (targetHeight != null && targetWidth != null) {
                size(targetHeight, targetWidth)
            }

            allowHardware(false)
        }

    }

    fun ImageView.loadImageFromFile(
                          filePath: String?,
                          placeholderImage: Int? = R.drawable.ic_placeholder_doc,
                          errorImage: Int? = null,
                          defaultImage: Int = R.drawable.ic_placeholder_doc,
                          transform: Transformation? = null,
                          targetWidth: Int? = null,
                          targetHeight: Int? = null) {
        filePath?.let { path ->
            val imgFile = File(path)
            if (imgFile.exists()) {
                this?.load(imgFile) {
                    placeholderImage?.let {
                        placeholder(it)
                    }

                    errorImage?.let {
                        error(it)
                    }

                    transform?.let {
                        transformations(it)
                    }

                    if (targetHeight != null && targetWidth != null) {
                        size(targetHeight, targetWidth)
                    }

                    allowHardware(false)
                }
            }
        } ?: run {
            this?.setImageResource(defaultImage)
        }
    }

    /**
     * imageLoadCallback : Success and Error callbacks when image loaded successfully
     */
    fun ImageView.loadImageWithCallback(
                              imageUrl: String?,
                              imageLoadCallback: ImageLoadCallback?,
                              placeholderImage: Int? = R.drawable.ic_placeholder_doc,
                              errorImage: Int? = null,
                              defaultImage: Int = R.drawable.ic_placeholder_doc,
                              transform: Transformation? = null,
                              targetWidth: Int? = null,
                              targetHeight: Int? = null) {
        if (imageUrl.isNullOrEmpty()) {
            this.setImageResource(defaultImage)
        } else {
            this.load(imageUrl) {
                placeholderImage?.let {
                    placeholder(it)
                }

                errorImage?.let {
                    error(it)
                }

                transform?.let {
                    transformations(it)
                }

                if (targetHeight != null && targetWidth != null) {

                    size(targetHeight, targetWidth)
                }

                imageLoadCallback?.let {
                    listener(object : ImageRequest.Listener {
                        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                            it.onSuccess()
                            super.onSuccess(request, result)
                        }

                        override fun onError(request: ImageRequest, result: ErrorResult) {
                            it.onError(result.throwable)
                            super.onError(request, result)
                        }
                    })
                }
                allowHardware(false)
            }
        }
    }

    fun ImageView.loadDrawable(drawable: Drawable?, transform: Transformation? = null, imageLoadCallback: ImageLoadCallback?=null) {
        drawable?.let {
            this.load(drawable) {
                transform?.let {
                    transformations(it)
                }
                imageLoadCallback?.let {
                    listener(object : ImageRequest.Listener {
                        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                            it.onSuccess()
                            super.onSuccess(request, result)
                        }

                        override fun onError(request: ImageRequest, result: ErrorResult) {
                            it.onError(result.throwable)
                            super.onError(request, result)
                        }
                    })
                }
                allowHardware(false)
            }
        } ?: run {
            this.setImageResource(R.drawable.ic_placeholder_doc)
        }
    }

    //callbacks
    interface ImageLoadCallback {
        fun onSuccess()
        fun onError(throwable: Throwable)
    }


}