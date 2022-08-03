package com.example.nycSchools.utils

// sealed class where I set up the 3 difference states that will use to update ui
sealed class UIState {
    class Success<T>(val response: T): UIState()
    class Error(val exception: Exception): UIState()
    object Loading: UIState()
}
