package com.play.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import com.play.data.model.LoginResponse
import com.play.data.repository.APIRepository
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val apiRepository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _user = MutableLiveData<Resource<LoginResponse>>()
    val loginUser: LiveData<Resource<LoginResponse>>
        get() = _user

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
}