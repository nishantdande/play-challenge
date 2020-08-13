package com.play.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.play.data.model.Story
import com.play.data.repository.APIRepository
import com.play.utils.NetworkHelper
import com.play.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val apiRepository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _story = MutableLiveData<Resource<Story>>()
    val getStory: LiveData<Resource<Story>>
        get() = _story

    init {
    }

    fun getStory(){
        viewModelScope.launch {
            _story.postValue(Resource.loading(null))
            _story.postValue(Resource.success(apiRepository.getStory()))
        }
    }

    fun clearStory() {
        apiRepository.clearStory()
    }
}