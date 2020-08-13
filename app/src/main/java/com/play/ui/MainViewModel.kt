package com.play.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import com.play.data.repository.APIRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val apiRepository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<ArrayList<String>>>()
    val users: LiveData<Resource<ArrayList<String>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {

                apiRepository.getTopStories().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }

            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }

    fun theme() : String{
        return apiRepository.getTheme();
    }
}