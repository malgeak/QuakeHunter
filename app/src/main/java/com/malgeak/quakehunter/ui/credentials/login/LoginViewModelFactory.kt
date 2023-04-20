package com.malgeak.quakehunter.ui.credentials.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malgeak.quakehunter.data.repository.user.UserRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(val repository: UserRepository) :
    ViewModelProvider.Factory {

    private val userRepository: UserRepository = repository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}