package com.play.data.api

import com.play.data.model.LoginResponse
import com.play.data.model.Story
import com.play.utils.NetworkHelper
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTopStories(): Call<ArrayList<String?>?> = apiService.getTopStories()

    override suspend fun getTopStories(pageCount: Int): Observable<ArrayList<Story>> {
        val count = AtomicInteger()

        return Observable.create(ObservableOnSubscribe {

            val call: Call<ArrayList<String?>?> = apiService.getTopStories()
            call.enqueue(object : Callback<java.util.ArrayList<String?>?> {
                override fun onResponse(
                    call: Call<java.util.ArrayList<String?>?>,
                    response: Response<java.util.ArrayList<String?>?>
                ) {
                    if (response.body() != null) {
                        var i = 0
                        val stories = java.util.ArrayList<Story>()
                        if (pageCount <= 22) {
                            val len = pageCount * 19
                            for (j in 0..len-1) {
                                response.body()!!.removeAt(j)
                            }
                        }
                        for (id in response.body()!!) {
                            if (i <= 20) {
                                val story: Call<Story> = apiService.getTopStories(id!!)
                                story.enqueue(object : Callback<Story?> {
                                    override fun onResponse(
                                        call: Call<Story?>,
                                        response: Response<Story?>
                                    ) {
                                        stories.add(response.body()!!)
                                        if (count.get() == 20) {
                                            it.onNext(stories)
                                            it.onComplete()
                                        }
                                        count.getAndIncrement()
                                    }

                                    override fun onFailure(
                                        call: Call<Story?>,
                                        t: Throwable
                                    ) {
                                        it.onError(Exception(t.message))
                                    }
                                })
                                i++
                            } else {
                                break
                            }
                        }
                    } else {
                        it.onError(Exception("Net Error"))
                    }
                }

                override fun onFailure(
                    call: Call<java.util.ArrayList<String?>?>,
                    t: Throwable
                ) {
//                    it.onError(NetworkHelper.is(t))
                }
            })

        });
    }


    override suspend fun login(username: String, password: String): Response<LoginResponse>
            = apiService.login(username, password);
}