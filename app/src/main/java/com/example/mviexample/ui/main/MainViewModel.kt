package com.example.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mviexample.model.BlogPost
import com.example.mviexample.model.User

import com.example.mviexample.ui.main.state.MainStateEvent
import com.example.mviexample.ui.main.state.MainStateEvent.*
import com.example.mviexample.ui.main.state.MainViewState
import com.example.mviexample.util.AbsentLiveData

class MainViewModel : ViewModel() {

    //_stateEvent is responsible for triggering different actions that we want to do
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()

    //_viewState is observing the different data models that are going to be visible in the View
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    // we observe viewState in the VM which is based on _viewState that is private to this class only
    val viewState: LiveData<MainViewState>
        get() = _viewState


    //dataState: we listen to the different state events (Transformations.switchMap)
    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->

            stateEvent?.let {
                handleStateEvent(it)
            }

        }

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

    fun setBlogListData(blogPost: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPost
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }


    fun setStateEvent(event: MainStateEvent){
        _stateEvent.value = event
    }
}