package com.malgeak.quakehunter.ui.credentials.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malgeak.quakehunter.data.repository.user.UserRepository

class SignUpViewModelFactory(val repository: UserRepository) : ViewModelProvider.Factory {

    private val userRepository: UserRepository = repository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}