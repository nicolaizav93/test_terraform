package md.endava.internship.budgetplanner.ui.registration

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentRegistrationPartThreeBinding
import md.endava.internship.budgetplanner.ui.dialog.ErrorDialogFragment
import md.endava.internship.budgetplanner.ui.dialog.LoadingDialogFragment
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class RegistrationPartThreeFragment : Fragment(), TextView.OnEditorActionListener {

    private lateinit var binding: FragmentRegistrationPartThreeBinding

    private var loadingDialog: LoadingDialogFragment = LoadingDialogFragment()
    private var errorDialog: ErrorDialogFragment = ErrorDialogFragment()

    private val commonViewModel: CommonRegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegistrationPartThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDefaultDropDown()
        handleAmountInteraction()
        initObservers()
        setDropDownItem()

        binding.endRegistration.setOnClickListener {
            commonViewModel.apply {
                setInitialAmount(binding.amountMoney.text.toString())
                endRegistration()
            }
        }
    }


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            commonViewModel.setInitialAmount(binding.amountMoney.text.toString())
            UIUtil.hideKeyboard(context, this.view); binding.amountMoney.clearFocus()
            return true
        }
        return false
    }

    private fun initObservers() {

        commonViewModel.isActiveButtonThree.observe(viewLifecycleOwner) { changeButton(it) }

        commonViewModel.initialAmountWithSymbol.observe(viewLifecycleOwner) {
            binding.amountMoney.setText(it)
        }
        commonViewModel.isRegister.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigate(R.id.action_registration_part_three_fragment_to_welcome_screen_fragment)
            }
        }
        commonViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) loadingDialog.show(childFragmentManager, LoadingDialogFragment.TAG)
            else if (loadingDialog.dialog?.isShowing == true) loadingDialog.dismiss()
        }
        commonViewModel.badRegistration.observe(viewLifecycleOwner) { badRequest ->
            if (badRequest) {
                errorDialog.message = commonViewModel.errorMessage.value.toString()
                errorDialog.show(childFragmentManager, ErrorDialogFragment.TAG)
            } else
                if (errorDialog.dialog?.isShowing == true) errorDialog.dismiss()
        }
    }

    private fun setDefaultDropDown() {
        val items = resources.getStringArray(R.array.items)
        val adapter = ArrayAdapter(requireContext(), R.layout.role_item, items)
        commonViewModel.apply {
            if (industryItem.value?.isNotEmpty() == true && industryItem.value != items[0]) {
                binding.dropdownMenu.apply {
                    editText?.setText(industryItem.value)
                    (editText as? AutoCompleteTextView)?.setAdapter(adapter)
                }
            } else {
                binding.dropdownMenu.apply {
                    editText?.setText(items[0])
                    (editText as? AutoCompleteTextView)?.setAdapter(adapter)
                }
            }
        }
    }

    private fun setDropDownItem() {
        commonViewModel.setIndustryItem(binding.suggestionList.text.toString())
        binding.suggestionList.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            commonViewModel.setIndustryItem(item)
        }
    }

    private fun handleAmountInteraction() {

        binding.suggestionList.setOnClickListener {
            UIUtil.hideKeyboard(context, it)
            commonViewModel.setInitialAmount(binding.amountMoney.text.toString())
        }

        binding.partThreeReg.setOnClickListener {
            commonViewModel.setInitialAmount(binding.amountMoney.text.toString())
            requireActivity().currentFocus?.clearFocus()
            UIUtil.hideKeyboard(context, this.view)
        }

        binding.amountMoney.apply {
            addTextChangedListener { commonViewModel.setActiveButton(it.toString()) }
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    setText(getString(R.string.symbol_dollar))
                    commonViewModel.setInitialAmount(binding.amountMoney.text.toString())
                    UIUtil.showKeyboard(context, this)
                }
            }
        }

        binding.amountMoney.setOnEditorActionListener(this)
    }

    private fun changeButton(isActive: Boolean) {
        binding.apply {
            endRegistration.apply { isClickable = isActive; alpha = if (isActive) 1.0f else 0.5f }
        }
    }


}

