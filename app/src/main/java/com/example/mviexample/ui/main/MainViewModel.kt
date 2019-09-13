package com.example.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mviexample.model.BlogPost

import com.example.mviexample.ui.main.state.MainStateEvent
import com.example.mviexample.ui.main.state.MainStateEvent.*
import com.example.mviexample.ui.main.state.MainViewState
import com.example.mviexample.util.AbsentLiveData

class MainViewModel : ViewModel() {
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when (stateEvent) {
            is GetBlockPostsEvent -> {
                return AbsentLiveData.create()
            }

            is GetUserEvent -> {
                return AbsentLiveData.create()
            }

            is None -> {
                return AbsentLiveData.create()
            }
        }
    }

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->

            stateEvent?.let {
                handleStateEvent(it)
            }

        }

    fun setBlogListData(blogPost: List<BlogPost>) {
        val update = getCurrnetViewStateOrNew()
        update.blogPosts
    }

    fun getCurrnetViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }
}