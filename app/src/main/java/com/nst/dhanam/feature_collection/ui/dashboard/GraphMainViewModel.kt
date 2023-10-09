package com.nst.dhanam.feature_collection.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GraphMainViewModel : ViewModel() {

    private val _backToGraphRootEvent = MutableSharedFlow<Unit>()
    val backToGraphRootEvent = _backToGraphRootEvent.asSharedFlow()
    fun submitBackToGraphRootEvent() {
        viewModelScope.launch {
            _backToGraphRootEvent.emit(Unit)
        }
    }
}