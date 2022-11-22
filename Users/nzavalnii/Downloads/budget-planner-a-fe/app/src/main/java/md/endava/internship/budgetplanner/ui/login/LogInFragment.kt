package md.endava.internship.budgetplanner.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentLogInBinding
import md.endava.internship.budgetplanner.ui.dialog.ErrorDialogFragment
import md.endava.internship.budgetplanner.ui.dialog.LoadingDialogFragment

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    private var loadingDialog: LoadingDialogFragment = LoadingDialogFragment()
    private var errorDialog: ErrorDialogFragment = ErrorDialogFragment()

    private val logInViewModel: LogInViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialogFragment()

        binding.apply {
            viewModel = logInViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setListener()
        setObserver()

        return binding.root
    }

    private fun setObserver() {
        logInViewModel.isValid.observe(viewLifecycleOwner) {
            binding.bSignIn.isClickable = it
        }

        logInViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                loadingDialog.show(
                    childFragmentManager, LoadingDialogFragment.TAG
                )
            } else {
                if (loadingDialog.dialog?.isShowing == true)
                    loadingDialog.dismiss()
            }
        }

        logInViewModel.showErrorDialog.observe(viewLifecycleOwner) { showError ->
            if (showError) {
                errorDialog.message = logInViewModel.errorMessage.value.toString()
                errorDialog.show(
                    childFragmentManager, ErrorDialogFragment.TAG
                )
                binding.etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_error_24,
                    0
                );
                binding.etEmail.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_error_24,
                    0
                );
            } else {
                binding.etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                binding.etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }

        logInViewModel.successLogin.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.action_log_in_fragment_to_dashboardFragment)
        }
    }

    private fun setListener() {
        binding.bSignIn.setOnClickListener {
            logInViewModel.login()
        }

        binding.tvSignUp.setOnClickListener {
            logInViewModel.clear()
            findNavController().navigate(R.id.action_log_in_fragment_to_registration_part_one_fragment)
        }
    }
}