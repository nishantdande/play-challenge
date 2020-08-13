package com.play.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import com.play.data.model.LoginResponse
import com.play.data.model.Story
import com.play.data.repository.APIRepository
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val apiRepository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _stories = MutableLiveData<Resource<ArrayList<Story>>>()
    val getStories: LiveData<Resource<ArrayList<Story>>>
        get() = _stories

    init {
    }

    fun stories(count : Int) {
        viewModelScope.launch {
            _stories.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {

                apiRepository.getTopStories(count).let {
                    if (it.isSuccessful) {
                        _stories.postValue(Resource.success(it.body()))
                    } else _stories.postValue(Resource.error(it.errorBody().toString(), null))
                }

            } else _stories.postValue(Resource.error("No internet connection", null))
        }
    }
}