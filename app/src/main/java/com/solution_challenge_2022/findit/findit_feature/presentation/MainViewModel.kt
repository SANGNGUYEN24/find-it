package com.solution_challenge_2022.findit.findit_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solution_challenge_2022.findit.findit_feature.domain.model.User

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun updateUser(user: User?) {
        _user.value = user
    }
}