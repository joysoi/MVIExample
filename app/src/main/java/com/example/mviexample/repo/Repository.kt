package com.example.mviexample.repo

import androidx.lifecycle.LiveData
import com.example.mviexample.api.RetrofitBuilder
import com.example.mviexample.model.BlogPost
import com.example.mviexample.model.User
import com.example.mviexample.ui.main.state.MainViewState
import com.example.mviexample.util.ApiSuccessResponse
import com.example.mviexample.util.DataState
import com.example.mviexample.util.GenericApiResponse
import retrofit2.Retrofit

object Repository {

    //retrieve BlogPost List
    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<BlogPost>, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }
        }.asLiveData()
    }

    //retrieve User Object
    fun getUser(userId: String): LiveData<DataState<MainViewState>> {

        return object : NetworkBoundResource<User, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(userId)
            }
        }.asLiveData()
    }
}