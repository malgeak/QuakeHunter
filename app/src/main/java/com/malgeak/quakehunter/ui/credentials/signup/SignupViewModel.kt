package com.malgeak.quakehunter.ui.credentials.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.malgeak.quakehunter.data.repository.user.UserRepository
import com.malgeak.quakehunter.ui.base.BaseViewModel
import com.malgeak.quakehunter.utils.ValidationsUtils
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _successSign: MutableLiveData<Boolean> = MutableLiveData()
    private val _usernameError: MutableLiveData<Boolean> = MutableLiveData()
    private val _passwordError: MutableLiveData<Boolean> = MutableLiveData()
    val successSign: LiveData<Boolean> = _successSign
    val usernameError: LiveData<Boolean> = _usernameError
    val passwordError: LiveData<Boolean> = _passwordError

    fun signUp(name: String, password: String, confirmPassword: String) {
        setLoader(true)
        if (isValidUser(name, password, confirmPassword)) {
            viewModelScope.launch {
                try {
                    _successSign.value = userRepository.signup(name, password)
                    setLoader(false)
                } catch (e: Exception) {
                    setLoader(false)
                    setAlertMessage(e.message.toString())
                }
            }
        }
    }

    fun isValidUser(name: String, password: String, confirmPassword: String): Boolean {

        var validation = false
        _usernameError.value = ValidationsUtils.validUserName(name)
        _passwordError.value = ValidationsUtils.validPassword(password, confirmPassword)

        if (_usernameError.value!! && _passwordError.value!!) {
            validation = true
        }

        return validation
    }
}