package md.endava.internship.budgetplanner.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import md.endava.internship.budgetplanner.data.model.User
import md.endava.internship.budgetplanner.data.repository.fromapi.AuthRepository
import md.endava.internship.budgetplanner.datastore.DataPreferences
import md.endava.internship.budgetplanner.network.Resource
import md.endava.internship.budgetplanner.utils.Constants
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val budgetPlannerRepository: AuthRepository,
    private val dataStore: DataPreferences
) : ViewModel() {

    private val _login = MutableLiveData("")
    val login: LiveData<String> = _login

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean> = _isValid

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showErrorDialog = MutableLiveData(false)
    val showErrorDialog: LiveData<Boolean> = _showErrorDialog

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _successLogin = MutableLiveData(false)
    val successLogin: LiveData<Boolean> = _successLogin

    private fun checkInputs() {
        _isValid.value = login.value?.isNotEmpty() ?: false
                && password.value?.isNotEmpty() ?: false
    }

    fun updateLogin(text: CharSequence) {
        _login.value = text.toString()
        checkInputs()
    }

    fun updatePassword(text: CharSequence) {
        _password.value = text.toString()
        checkInputs()
    }

    fun login() {
        _isLoading.value = true

        viewModelScope.launch {
            val result = budgetPlannerRepository.signIn(
                User(
                    login = login.value.toString(),
                    password = password.value.toString()
                )
            )
            _isLoading.value = false
            if (result is Resource.Success) {
                dataStore.saveToken(result.value)
                _successLogin.value = true
            } else if (result is Resource.Failure) {
                _errorMessage.value = Constants.error_login
                _showErrorDialog.value = true
            }
        }
    }

    fun clear() {
        _showErrorDialog.value = false
        _isLoading.value = false
        _isValid.value = false
        _successLogin.value = false
        _login.value = null
        _password.value = null
        _errorMessage.value = ""
    }
}