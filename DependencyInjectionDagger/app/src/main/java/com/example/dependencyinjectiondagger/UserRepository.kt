package com.example.dependencyinjectiondagger

import javax.inject.Inject


class UserRepository @Inject constructor() {
    fun getUserData(): String {
        return "User data from repository"
    }
}

