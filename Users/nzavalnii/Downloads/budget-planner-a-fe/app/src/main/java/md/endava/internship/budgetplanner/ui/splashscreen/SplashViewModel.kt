package md.endava.internship.budgetplanner.ui.splashscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import md.endava.internship.budgetplanner.datastore.DataPreferences
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val preferences: DataPreferences) : ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _isValidToken = MutableLiveData<Boolean>()
    val isValidToken: LiveData<Boolean> = _isValidToken

    fun getToken() {
        viewModelScope.launch {
            _token.value = preferences.token.first() ?: "null"
            validateToken()
        }
    }

    private fun validateToken() {
        if (token.value != "null")
        _isValidToken.value = token.value?.isNotEmpty()
    }
}