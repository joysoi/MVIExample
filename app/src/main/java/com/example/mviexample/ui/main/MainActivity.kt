package com.example.mviexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mviexample.R
import com.example.mviexample.ui.DataStateListener
import com.example.mviexample.util.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    DataStateListener {

    lateinit var viewModel: MainViewModel

    override fun onDataStateChange(dataState: DataState<*>?) {
        handelDataStateChange(dataState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "Main Fragment"
            )
            .commit()
    }

    private fun handelDataStateChange(dataState: DataState<*>?) {
        dataState?.let {

            // handle loading
              showProgressBar(it.loading)

            // handle Message
            dataState.message?.let{ message ->
                showToast(message)
            }
        }
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            progress_bar.visibility = View.VISIBLE
        }
        else{
            progress_bar.visibility = View.INVISIBLE
        }
    }
}
