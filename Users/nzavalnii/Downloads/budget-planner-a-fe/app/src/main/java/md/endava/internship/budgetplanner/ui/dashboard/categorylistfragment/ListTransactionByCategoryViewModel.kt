package md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListTransactionByCategoryViewModel : ViewModel() {

    private val _type = MutableLiveData<String>()
    val type: LiveData<String> = _type

    private val _list = MutableLiveData<ArrayList<TransactionModel>>()
    val list: LiveData<ArrayList<TransactionModel>> = _list

    private val _listVisibility = MutableLiveData(false)
    val listVisibility: LiveData<Boolean> = _listVisibility

    fun loadList(newList: ArrayList<TransactionModel>) {
        _list.value = newList
        _listVisibility.value = newList.isNullOrEmpty()
    }

    fun updateType(newType: String) {
        _type.value = newType
    }


}