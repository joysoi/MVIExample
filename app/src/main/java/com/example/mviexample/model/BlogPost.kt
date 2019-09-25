package com.example.mviexample.model

import androidx.lifecycle.LiveData
import com.example.mviexample.ui.main.state.MainViewState
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class: represents simple class as data container
 *
 * If you give default values to all the fields - empty constructor is generated automatically by Kotlin
 *
 * declaring a second constructor:
 *   constructor() : this(
                // give values to all fields by their order
        )
 */

/**
 * getter and setter are optional.
 * If we define a custom getter,
 * it will be called every time we access the property
 * Example:
    val isEmpty: Boolean
    get() = this.size == 0

 *If we define a custom setter,
 *  it will be called every time we assign a value to the property.
 *  Example:
 *  var stringRepresentation: String
    get() = this.toString()
    set(value) {
    setDataFromString(value) // parses the string and assigns values to other properties
}
 */


data class BlogPost(

    @Expose
    @SerializedName("pk")
    val pk: Int? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("body")
    val body: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
)
{
    override fun toString(): String {
        return "BlogPost(title=$title, body=$body, image=$image)"
    }
}