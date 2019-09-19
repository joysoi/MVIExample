package com.example.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mviexample.model.BlogPost
import com.example.mviexample.model.User
import com.example.mviexample.repo.Repository

import com.example.mviexample.ui.main.state.MainStateEvent
import com.example.mviexample.ui.main.state.MainStateEvent.*
import com.example.mviexample.ui.main.state.MainViewState
import com.example.mviexample.util.AbsentLiveData
import com.example.mviexample.util.DataState

class MainViewModel : ViewModel() {

    //_stateEvent is responsible for triggering different actions that we want to do
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()

    //_viewState is observing the different data models that are going to be visible in the View
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    // we observe viewState in the VM which is based on _viewState that is private to this class only
    val viewState: LiveData<MainViewState>
        get() = _viewState


    //dataState: we listen to the different state events (Transformations.switchMap)
    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>>{
        println("DEBUG: New StateEvent detected: $stateEvent")
        when(stateEvent){

            is GetBlockPostsEvent -> {
                return Repository.getBlogPosts()
            }

            is GetUserEvent -> {
                return Repository.getUser(stateEvent.userId)
            }

            is None ->{
                return AbsentLiveData.create()
            }
        }
    }

    fun setBlogListData(blogPosts: List<BlogPost>){
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let{
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent){
        val state: MainStateEvent
        state = event
        _stateEvent.value = state
    }
}