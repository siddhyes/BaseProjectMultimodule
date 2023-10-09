

package com.nst.baseproject.common.util

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Collects data from a Flow within the context of a [Fragment]'s lifecycle.
 *
 * @param flow The Flow of data elements to collect.
 * @param collect A suspending lambda function to process each collected data element.
 */
fun <T> Fragment.collectInLifecycle(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}

/**
 * Collects the latest data from a Flow within the context of a [Fragment]'s lifecycle.
 *
 * @param flow The Flow of data elements to collect the latest value from.
 * @param collect A suspending lambda function to process the latest collected data element.
 */
fun <T> Fragment.collectLatestInLifecycle(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

/**
 * Collects data from a Flow within the context of a [ComponentActivity]'s lifecycle.
 *
 * @param flow The Flow of data elements to collect.
 * @param collect A suspending lambda function to process each collected data element.
 */
fun <T> ComponentActivity.collectInLifecycle(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}

/**
 * Collects the latest data from a Flow within the context of a [ComponentActivity]'s lifecycle.
 *
 * @param flow The Flow of data elements to collect the latest value from.
 * @param collect A suspending lambda function to process the latest collected data element.
 */
fun <T> ComponentActivity.collectLatestInLifecycle(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}
 