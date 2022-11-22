package md.endava.internship.budgetplanner.ui.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import md.endava.internship.budgetplanner.data.model.RegistrationPost
import md.endava.internship.budgetplanner.data.repository.fromapi.AuthRepository
import md.endava.internship.budgetplanner.network.Resource
import md.endava.internship.budgetplanner.utils.validator.AppValidator
import java.text.NumberFormat
import javax.inject.Inject

@HiltViewModel
class CommonRegistrationViewModel @Inject constructor(
    private val validator: AppValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isActiveButtonOne = MutableLiveData(false)
    val isActiveButtonOne: LiveData<Boolean> = _isActiveButtonOne

    private val _badRegistration = MutableLiveData(false)
    val badRegistration: LiveData<Boolean> = _badRegistration

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _confirmPass = MutableLiveData<String>()
    val confirmPass: LiveData<String> = _confirmPass

    private val _inputsStatesOne = MutableLiveData<MutableMap<String, String>>()
    val inputsStatesOne: LiveData<MutableMap<String, String>> = _inputsStatesOne

    private val _firstName = MutableLiveData<String>()
    var firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    var lastName: LiveData<String> = _lastName

    private val _isActiveButtonTwo = MutableLiveData(false)
    val isActiveButtonTwo: LiveData<Boolean> = _isActiveButtonTwo

    private val _inputsStatesTwo = MutableLiveData<MutableMap<String, String>>()
    val inputsStatesTwo: LiveData<MutableMap<String, String>> = _inputsStatesTwo

    private val _isRegister = MutableLiveData(false)
    val isRegister: LiveData<Boolean> = _isRegister

    private val _isActiveButtonThree = MutableLiveData(false)
    val isActiveButtonThree: LiveData<Boolean> = _isActiveButtonThree

    private val _industryItem = MutableLiveData<String>()
    var industryItem: LiveData<String> = _industryItem

    private val _initialAmountWithSymbol = MutableLiveData<String>()
    var initialAmountWithSymbol: LiveData<String> = _initialAmountWithSymbol

    private val _errorMessage = MutableLiveData("Something went wrong")
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _amount = MutableLiveData<Double>()
    val amount: LiveData<Double> = _amount

    fun setEmail(email: String) {
        _email.value = email; changeButtonOne()
    }

    fun setPassword(password: String) {
        _pass.value = password; changeButtonOne()
    }

    fun setConfirmPassword(confirmPass: String) {
        _confirmPass.value = confirmPass; changeButtonOne()
    }

    private fun changeButtonOne() {
        _isActiveButtonOne.value =
            email.value?.isNotEmpty() == true && pass.value?.isNotEmpty() == true && confirmPass.value?.isNotEmpty() == true
    }

    fun checkValidationOne() {

        val error = mutableMapOf<String, String>()

        if (!validator.validatePassword(pass.value.toString(), confirmPass.value.toString()))
            error["PASSWORD"] = "Please enter a valid password address"

        if (!validator.validateEmail(email.value.toString()))
            error["EMAIL"] = "Please enter a valid email address"

        _inputsStatesOne.value = error
    }

    fun setFirstName(firstName: String) {
        _firstName.value = firstName; changeButtonTwo()
    }

    fun setLastName(lastName: String) {
        _lastName.value = lastName; changeButtonTwo()
    }

    private fun changeButtonTwo() {
        _isActiveButtonTwo.value =
            firstName.value?.isNotEmpty() == true && lastName.value?.isNotEmpty() == true
    }

    fun checkValidationTwo() {

        val errors = mutableMapOf<String, String>()

        if (validator.validateName(firstName.value.toString())["inRange"] == false) {
            errors["FIRST_NAME_RANGE"] = "The first name must be greater than 3 or equal to 22"
        }

        if (validator.validateName(lastName.value.toString())["inRange"] == false) {
            errors["LAST_NAME_RANGE"] = "The last name must be greater than 3 or equal to 22"
        }

        if (validator.validateName(firstName.value.toString())["isValid"] == false) {
            errors["FIRST_NAME"] = "First name should contain only alpha characters"
        }

        if (validator.validateName(lastName.value.toString())["isValid"] == false) {
            errors["LAST_NAME"] = "Last name should contain only alpha characters"
        }
        _inputsStatesTwo.value = errors
    }

    fun setIndustryItem(item: String) {
        _industryItem.value = item
    }

    fun setInitialAmount(amount: String) {
        val initialAmount = amount.trim('$').toDoubleOrNull() ?: return
        val initAmount = NumberFormat.getCurrencyInstance().format(initialAmount)
        _initialAmountWithSymbol.value = NumberFormat.getCurrencyInstance().format(initialAmount)
        _amount.value = initAmount.trim('$').replace(",", "").toDouble()
    }

    fun endRegistration() {
        val user = RegistrationPost(
            firstName.value,
            lastName.value,
            industryItem.value,
            email.value,
            pass.value,
            confirmPass.value,
            amount.value
        )
        viewModelScope.launch {
            _isLoading.value = true
            val callRegistration = authRepository.signUp(user)

            if (callRegistration is Resource.Failure) {
                _errorMessage.value = "This user already exist"
                _badRegistration.value = true
            } else if (callRegistration is Resource.Success) {
                _isRegister.value = true
            }
            _isLoading.value = false
        }
    }

    fun setActiveButton(item: String) {
        _isActiveButtonThree.value = item.length > 1
    }


}