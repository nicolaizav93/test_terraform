package md.endava.internship.budgetplanner.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import md.endava.internship.budgetplanner.R


class LoadingDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.loading_dialog_fragment)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);

        return dialog
    }


    companion object {
        const val TAG = "LoadingDialogFragment"
    }
}