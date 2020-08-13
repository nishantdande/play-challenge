package com.play.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.play.utils.NetworkHelper
import com.play.utils.Resource
import com.play.data.model.Story
import com.play.data.repository.APIRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            apiRepository.getTopStories(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _stories.postValue(Resource.success(it))
                }, {
                    _stories.postValue(Resource.error(it.localizedMessage, null))
                });
            } else _stories.postValue(Resource.error("No internet connection", null))
        }
    }

    fun storeStory(story: Story){
        apiRepository.setStory(story);
    }
}