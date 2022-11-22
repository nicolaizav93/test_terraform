package md.endava.internship.budgetplanner.ui.registration

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import md.endava.internship.budgetplanner.databinding.FragmentRegistrationPartOneBinding

class RegistrationPartOneFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationPartOneBinding

    private val partOneViewModel: CommonRegistrationViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationPartOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingListener()
        initListeners()
        partOneViewModel.isActiveButtonOne.observe(viewLifecycleOwner) { changeButton(it) }
        binding.continueButton.setOnClickListener { nextPart() }
    }


    private fun initListeners() {
        validationConfirmPasswordListener(); validationEmailListener(); validationPasswordListener()
    }

    private fun validationEmailListener() {
        binding.apply {
            emailEditText.addTextChangedListener {
                setInputs(emailText)
                partOneViewModel.setEmail(it.toString())
            }
        }
    }

    private fun validationPasswordListener() {
        binding.apply {
            passwordEditText.addTextChangedListener {
                setInputs(confirmPasswordText); setInputs(passwordText)
                partOneViewModel.setPassword(it.toString())
            }
        }
    }

    private fun validationConfirmPasswordListener() {
        binding.apply {
            confirmPasswordEditText.addTextChangedListener {
                setInputs(passwordText); setInputs(confirmPasswordText)
                partOneViewModel.setConfirmPassword(it.toString())
            }
        }
    }

    private fun settingListener() {
        binding.signInText.setOnClickListener {
            val nexAction =
                RegistrationPartOneFragmentDirections.actionRegistrationPartOneFragmentToLogInFragment()
            findNavController().navigate(nexAction)
        }
    }

    private fun nextPart() {

        partOneViewModel.apply {
            checkValidationOne()
            inputsStatesOne.apply {
                if (value?.isNotEmpty() == true) {
                    observe(viewLifecycleOwner) { bindErrors(it) }
                } else next()
            }
        }
    }

    private fun next() {
        val direction = RegistrationPartOneFragmentDirections
        val nextAction = direction.actionRegistrationPartOneFragmentToRegistrationPartTwoFragment()
        findNavController().navigate(nextAction)
    }

    private fun bindErrors(validatorStates: MutableMap<String, String>) {

        validatorStates["EMAIL"]?.let { setError(binding.emailText, it) }
        validatorStates["PASSWORD"]?.let {
            binding.apply {
                setError(passwordText, it)
                setError(confirmPasswordText, it)
            }
        }
    }

    private fun changeButton(isActive: Boolean) {
        binding.continueButton.apply {
            isClickable = isActive; alpha = if (isActive) 1.0f else 0.5f
        }
    }

    private fun setInputs(
        input: TextInputLayout
    ) = input.apply { editText?.apply { setTextColor(Color.WHITE); error = null }; error = null }

    private fun setError(
        input: TextInputLayout, msg: String
    ) = input.apply { editText?.apply { setTextColor(Color.RED) }; error = msg }

}