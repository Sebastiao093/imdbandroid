package com.example.imdb.register.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    private val _nameRegister = MutableLiveData<String>()
    val nameRegister: LiveData<String> = _nameRegister

    private val _emailRegister = MutableLiveData<String>()
    val emailRegister: LiveData<String> = _emailRegister

    private val _passwordRegister = MutableLiveData<String>()
    val passwordRegister: LiveData<String> = _passwordRegister

    private val _isLoginEnableRegister = MutableLiveData<Boolean>()
    val isLoginEnableRegister: LiveData<Boolean> = _isLoginEnableRegister

    private val _passwordVisibilityRegister = MutableLiveData<Boolean>()
    val passwordVisibilityRegister: LiveData<Boolean> = _passwordVisibilityRegister


    fun onRegisterChanged(name: String, email: String, password: String){
        _nameRegister.value = name
        _emailRegister.value = email
        _passwordRegister.value = password
        _isLoginEnableRegister.value = enableLogin(name = name, email = email, password = password)
    }

    fun enableLogin(name: String, email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6 && name.length > 2

    fun changePasswordVisibility(passwordVisibility: Boolean){
        _passwordVisibilityRegister.value = !passwordVisibility
    }
}