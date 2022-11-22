package md.endava.internship.budgetplanner.ui.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import md.endava.internship.budgetplanner.ui.transaction.TransactionFragment
import java.util.*

const val YEAR_MILLI: Long = 31556952000

class DataPickerDialogFragment : DialogFragment() {

    lateinit var transactionFragment: TransactionFragment

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val dayOfMonth: Int = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            STYLE_NORMAL,
            transactionFragment,
            year,
            month,
            dayOfMonth
        )

        dialog.datePicker.maxDate = c.timeInMillis
        dialog.datePicker.minDate = c.timeInMillis - YEAR_MILLI
        dialog.datePicker.tag = tag

        return dialog
    }

    companion object {
        const val TAG = "DataPickerDialogFragment"
    }

}