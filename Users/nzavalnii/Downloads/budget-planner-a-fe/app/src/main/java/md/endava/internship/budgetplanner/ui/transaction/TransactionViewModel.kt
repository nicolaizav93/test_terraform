package md.endava.internship.budgetplanner.ui.transaction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import md.endava.internship.budgetplanner.data.model.transaction.TransactionPost
import md.endava.internship.budgetplanner.data.repository.fromapi.TransactionRepository
import md.endava.internship.budgetplanner.network.Resource
import md.endava.internship.budgetplanner.utils.Category
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val budgetPlannerRepository: TransactionRepository,
) : ViewModel() {

    private var validTitle = false
    private var validAmount = false

    private var _type = MutableLiveData("Expense")
    var type: LiveData<String> = _type

    private var _title = MutableLiveData<String>()
    var title: LiveData<String> = _title

    private var _errorTitle = MutableLiveData<String?>()
    var errorTitle: MutableLiveData<String?> = _errorTitle

    private var _amount = MutableLiveData<String>()
    var amount: LiveData<String> = _amount

    private var _errorAmount = MutableLiveData<String?>()
    var errorAmount: LiveData<String?> = _errorAmount

    private var _date = MutableLiveData<String>()
    var date: LiveData<String> = _date

    private var _dateFormatted = MutableLiveData<String>()
    var dateFormatted: LiveData<String> = _dateFormatted

    private var _note = MutableLiveData<String>()
    var note: LiveData<String> = _note

    private var _isValid = MutableLiveData(false)
    var isValid: LiveData<Boolean> = _isValid

    private var _selectedCategory = MutableLiveData<Category?>()
    var selectedCategory: LiveData<Category?> = _selectedCategory

    private var _categoryList = MutableLiveData<Array<Category>>()
    var categoryList: LiveData<Array<Category>> = _categoryList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showSuccessDialog = MutableLiveData(false)
    val showSuccessDialog: LiveData<Boolean> = _showSuccessDialog

    fun updateSelectedCategoryId(newId: Category?) {
        _selectedCategory.value = newId

        checkValidation()
    }

    fun updateType(newType: String) {
        _type.value = newType

        if (newType == "Expense") {
            _categoryList.value = Category.values().copyOfRange(0, 6)
        } else {
            _categoryList.value = Category.values().copyOfRange(6, 10)
        }

        checkValidation()
    }

    fun updateTitle(text: CharSequence) {
        _title.value = text.toString()

        validTitle = text.length <= 5
        if (validTitle) {
            _errorTitle.value = "The title can not be less than 5 characters long."
        } else {
            _errorTitle.value = null
        }

        checkValidation()
    }

    fun updateAmount(text: CharSequence) {
        _amount.value = text.toString()

        validAmount = text.toString().toFloatOrNull() ?: 0.0f < 1.00f

        if (validAmount) {
            _errorAmount.value = "The amount can not be less than 1.00."
        } else {
            _errorAmount.value = null
        }

        checkValidation()
    }

    fun updateNote(text: CharSequence) {
        _note.value = text.toString()
        checkValidation()
    }

    fun updateDate(day: Int, month: Int, year: Int) {
        _date.value = "$day/${month + 1}/$year"
        _dateFormatted.value = DateTime(year, month+1, day, 0, 0, 0, 1).toLocalDateTime().toString() + "Z"
        checkValidation()
    }

    private fun checkValidation() {
        _isValid.value = validAmount == false &&
                validTitle == false &&
                date.value?.isNotEmpty() ?: false &&
                selectedCategory.value != null
    }

    fun save() {
        _isLoading.value = true

        viewModelScope.launch {

            val result = budgetPlannerRepository.sendTransaction(
                TransactionPost(
                    title = title.value.toString(),
                    amount = amount.value?.toDouble(),
                    date = dateFormatted.value.toString(),
                    note = note.value.toString(),
                    type = type.value.toString(),
                    category = selectedCategory.value?.key
                )
            )
            _isLoading.value = false

            if (result is Resource.Success<*>) {
                _showSuccessDialog.value = true
            }
        }
    }

    fun reset() {
        _isLoading.value = false
        _showSuccessDialog.value = false
        _title.value = ""
        _amount.value = ""
        _note.value = ""
        _date.value = ""
        _selectedCategory.value = null
        validAmount = false
        validTitle = false
    }


}