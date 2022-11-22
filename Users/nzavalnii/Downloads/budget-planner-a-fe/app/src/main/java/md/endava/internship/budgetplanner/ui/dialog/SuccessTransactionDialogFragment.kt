package md.endava.internship.budgetplanner.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import md.endava.internship.budgetplanner.R

class SuccessTransactionDialogFragment(
    private val onDoneClick: () -> Unit,
    private val onAddAnotherClick: () -> Unit
) : DialogFragment() {

    var amount: String = ""
    var type: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = layoutInflater.inflate(R.layout.success_dialog_fragment, null, false)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        view.apply {
            findViewById<TextView>(R.id.tv_message).text = getString(R.string.succes_transaction_message, type, amount)

            findViewById<Button>(R.id.b_done).setOnClickListener { dismiss(); onDoneClick() }

            findViewById<Button>(R.id.b_add).setOnClickListener { dismiss(); onAddAnotherClick() }
        }

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        return dialog
    }


    companion object {
        const val TAG = "SuccessTransactionDialogFragment"
    }
}