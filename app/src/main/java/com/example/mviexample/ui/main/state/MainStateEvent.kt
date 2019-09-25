package com.example.mviexample.ui.main.state

/**
 *Sealed classes are classes where objects can only be of one type.
 * The main advantage is that objects from sealed classes can keep their state
 */

sealed class MainStateEvent {

    /**
     * A sealed class can have subclasses,
     * but all of them must be declared in the same file as the sealed class itself
     */

    //State Events:

    class GetBlogPostsEvent : MainStateEvent()

    class GetUserEvent(
        val userId: String
    ) : MainStateEvent()

    class None : MainStateEvent()
}