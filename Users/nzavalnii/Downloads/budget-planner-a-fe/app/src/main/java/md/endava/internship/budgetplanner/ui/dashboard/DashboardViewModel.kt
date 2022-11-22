package md.endava.internship.budgetplanner.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.data.repository.fromapi.BalanceRepository
import md.endava.internship.budgetplanner.data.repository.fromapi.TransactionRepository
import md.endava.internship.budgetplanner.datastore.DataPreferences
import md.endava.internship.budgetplanner.network.Resource
import md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment.TransactionModel
import md.endava.internship.budgetplanner.utils.Constants
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val balanceRepository: BalanceRepository,
    private val dataStore: DataPreferences
) : ViewModel() {

    private val _type = MutableLiveData("Expense")
    val type: LiveData<String> = _type

    private val _listExpense = MutableLiveData<ArrayList<TransactionModel>>()
    val listExpense: LiveData<ArrayList<TransactionModel>> = _listExpense

    private val _listIncome = MutableLiveData<ArrayList<TransactionModel>>()
    val listIncome: LiveData<ArrayList<TransactionModel>> = _listIncome

    private val _chartList = MutableLiveData<ArrayList<PieEntry>>()
    val chartList: MutableLiveData<ArrayList<PieEntry>> = _chartList

    private val _chartColor = MutableLiveData<ArrayList<Int>>()
    val chartColor: MutableLiveData<ArrayList<Int>> = _chartColor

    private val _showChart = MutableLiveData(true)
    val showChart: LiveData<Boolean> = _showChart

    private val _centerText = MutableLiveData<String>()
    val centerText: LiveData<String> = _centerText

    private val _balance = MutableLiveData("$0.0")
    val balance: LiveData<String> = _balance


    fun loadTransaction() {
        viewModelScope.launch {
            val transaction = transactionRepository.getTransactions()
            if (transaction is Resource.Success) {

                val incomes = Constants.defaultArrayIncome
                val expenses = Constants.defaultArrayExpense

                incomes.forEach { it.count = 0; it.sum = 0.0 }

                expenses.forEach { it.count = 0; it.sum = 0.0 }

                transaction.value.transactions?.map { item ->
                    if (item?.type == "Income") {
                        incomes.forEach {
                            if (it.category.key == item.category) {
                                it.count++
                                it.sum += item.amount?.toDouble() ?: 0.0
                            }
                        }
                    } else {
                        expenses.forEach {
                            if (it.category.key == item?.category) {
                                it.count++
                                it.sum += item.amount?.toDouble() ?: 0.0
                            }
                        }
                    }
                }



                _listExpense.value =
                    ArrayList(expenses.filter { it.count > 0 } as ArrayList<TransactionModel>)
                _listIncome.value =
                    ArrayList(incomes.filter { it.count > 0 } as ArrayList<TransactionModel>)

                setType("Expense")
            }
        }
    }

    fun updateDataShowerType() {
        _showChart.value?.let {
            _showChart.value = !it
        }
    }

    fun setType(newType: String) {
        _type.value = newType
        val newListChart = arrayListOf<PieEntry>()
        val colors = arrayListOf<Int>()
        var sum = 0.0
        if (newType == "Expense") {
            listExpense.value?.forEach {
                newListChart.add(PieEntry(it.sum.toFloat()))
                colors.add(it.category.color)
                sum -= it.sum
            }
        } else {
            listIncome.value?.forEach {
                newListChart.add(PieEntry(it.sum.toFloat()))
                colors.add(it.category.color)
                sum += it.sum
            }
        }
        _centerText.value = prettyCount(sum)

        if (newListChart.isNullOrEmpty()) {
            newListChart.add(PieEntry(1F))
            colors.add(R.color.text_hide_color)
        }

        _chartColor.value = colors
        _chartList.value = newListChart
    }

    private fun prettyCount(count: Double): String {
        return if (count > 1000 || count < -1000) {
            DecimalFormat("$#0.0").format(count / 1000) + "k"
        } else {
            DecimalFormat("$#,##0").format(count).toString()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            dataStore.saveToken("")
        }
    }

    fun loadBalance() {
        viewModelScope.launch {
            val result = balanceRepository.getBalance()

            if (result is Resource.Success) {
                _balance.value = result.value.balance?.let { prettyCount(it) }
            }
        }
    }
}