package com.play.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.play.utils.NetworkHelper
import com.play.utils.Resource
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
    }

    fun theme() : String{
        return apiRepository.getTheme();
    }
}