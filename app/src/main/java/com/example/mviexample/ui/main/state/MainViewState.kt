package com.example.mviexample.ui.main.state

import com.example.mviexample.model.Blockpost
import com.example.mviexample.model.User

data class MainViewState(

    var BlockPost : List<Blockpost>? = null,
    var User : User? = null
)