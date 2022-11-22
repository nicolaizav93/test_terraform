package md.endava.internship.budgetplanner.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import md.endava.internship.budgetplanner.R

class ErrorDialogFragment : DialogFragment() {

    var message : String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = layoutInflater.inflate(R.layout.error_dialog_fragment, null, false)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        view.findViewById<TextView>(R.id.tv_message).text = message

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        view.findViewById<Button>(R.id.b_got_it).setOnClickListener {
            dismiss()
        }

        return dialog
    }


    companion object {
        const val TAG = "ErrorDialogFragment"
    }
}