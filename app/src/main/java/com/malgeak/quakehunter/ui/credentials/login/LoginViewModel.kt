package com.malgeak.quakehunter.ui.credentials.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.malgeak.quakehunter.data.repository.user.UserRepository
import com.malgeak.quakehunter.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

class LoginViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _successLogin: MutableLiveData<Boolean> = MutableLiveData()
    val successLogin: LiveData<Boolean> = _successLogin

    fun login(name: String, password: String) {
        viewModelScope.launch {
            try {
                _successLogin.value = userRepository.login(name, password)
            } catch (e: Exception) {
                setAlertMessage(e.message.toString())
            }
        }
    }

    fun existUser(): Boolean {
        var validation: Boolean = true
        runBlocking {
            validation = userRepository.checkUser()
        }

        return validation
    }
}