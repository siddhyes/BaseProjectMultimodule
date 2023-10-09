package com.nst.baseproject.common.util

sealed interface UiEvent {
    data class SuccessEvent(val message: UiText) : UiEvent
    data class ErrorEvent(val message: UiText) : UiEvent
}