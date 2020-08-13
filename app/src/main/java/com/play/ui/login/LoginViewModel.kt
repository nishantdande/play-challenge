package com.play.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import com.play.data.model.LoginResponse
import com.play.data.repository.APIRepository
import com.play.utils.CommonUtils
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val apiRepository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _user = MutableLiveData<Resource<LoginResponse>>()
    val loginUser: LiveData<Resource<LoginResponse>> get() = _user

    private val _theme = MutableLiveData<Resource<String>>()
    val userTheme: LiveData<Resource<String>> get() = _theme

    private val _validation = MutableLiveData<Resource<String>>()
    val userValidation: LiveData<Resource<String>> get() = _validation

    init {
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _user.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {

                apiRepository.login(username, password).let {
                    if (it.isSuccessful) {
                        _user.postValue(Resource.success(it.body()))
                    } else _user.postValue(Resource.error(it.errorBody().toString(), null))
                }

            } else _user.postValue(Resource.error("No internet connection", null))
        }
    }

    fun getTheme() {
        viewModelScope.launch {
            _theme.postValue(Resource.loading(null))
            _theme.postValue(Resource.success(apiRepository.getTheme()))
        }
    }

    fun theme(theme: String) {
        apiRepository.setTheme(theme);
    }

    fun checkValidation(username: String, password: String){
        viewModelScope.launch{
            if (username.isEmpty()) {
                _validation.postValue(Resource.error(com.play.R.string.empty_email, null));
                return@launch;
            }
            if (!CommonUtils.isEmailValid(username)) {
                _validation.postValue(Resource.error(com.play.R.string.invalid_email, null));
                return@launch;
            }
            if (password == null || password.isEmpty()) {
                _validation.postValue(Resource.error(com.play.R.string.empty_password, null));
                return@launch;
            }

            _validation.postValue(Resource.success("true"))
        }
    }
}