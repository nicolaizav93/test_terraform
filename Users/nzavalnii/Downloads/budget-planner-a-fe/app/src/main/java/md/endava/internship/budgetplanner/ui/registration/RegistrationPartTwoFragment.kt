package md.endava.internship.budgetplanner.ui.registration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import md.endava.internship.budgetplanner.databinding.FragmentRegistrationPartTwoBinding

class RegistrationPartTwoFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationPartTwoBinding

    private val partTwoViewModel: CommonRegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationPartTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        partTwoViewModel.isActiveButtonTwo.observe(viewLifecycleOwner) { changeButton(it) }
        binding.continueButtonTwo.setOnClickListener { nextPart() }
        initListeners()
        initSafeInputs()

    }

    private fun initSafeInputs() {
        partTwoViewModel.apply {
            if (firstName.value != "null" && lastName.value != "null") {
                binding.apply {
                    firstNameEditText.setText(firstName.value)
                    lastNameEditText.setText(lastName.value)
                }
            }
        }
    }

    private fun initListeners() {
        validationLastNameListener(); validationFirstNameListener()
    }

    private fun validationFirstNameListener() {
        binding.apply {
            firstNameEditText.addTextChangedListener {
                setInputs(firstNameInputLayout)
                partTwoViewModel.setFirstName(it.toString())
            }
        }
    }

    private fun validationLastNameListener() {
        binding.apply {
            lastNameEditText.addTextChangedListener {
                setInputs(lastNameInputLayout)
                partTwoViewModel.setLastName(it.toString())
            }
        }
    }

    private fun nextPart() {
        partTwoViewModel.apply {
            checkValidationTwo()
            inputsStatesTwo.apply {
                if (value?.isNotEmpty() == true) {
                    observe(viewLifecycleOwner) { bindErrors(it) }
                } else next()
            }
        }
    }

    private fun next() {
        val direction = RegistrationPartTwoFragmentDirections
        val nextAction =
            direction.actionRegistrationPartTwoFragmentToRegistrationPartThreeFragment()
        findNavController().navigate(nextAction)
    }

    private fun bindErrors(validatorStates: MutableMap<String, String>) {
        Log.e("test", validatorStates.toString())
        validatorStates.apply {
            get("FIRST_NAME")?.let { setError(binding.firstNameInputLayout, it) }
            get("LAST_NAME")?.let { setError(binding.lastNameInputLayout, it) }
            get("FIRST_NAME_RANGE")?.let { setError(binding.firstNameInputLayout, it) }
            get("LAST_NAME_RANGE")?.let { setError(binding.lastNameInputLayout, it) }
        }
    }

    private fun changeButton(isActive: Boolean) {
        binding.apply {
            continueButtonTwo.apply {
                isClickable = isActive; alpha = if (isActive) 1.0f else 0.5f
            }
        }
    }

    private fun setInputs(
        input: TextInputLayout
    ) = input.apply { editText?.apply { setTextColor(Color.WHITE); error = null }; error = null }

    private fun setError(
        input: TextInputLayout, msg: String
    ) = input.apply { editText?.apply { setTextColor(Color.RED) }; error = msg }


}