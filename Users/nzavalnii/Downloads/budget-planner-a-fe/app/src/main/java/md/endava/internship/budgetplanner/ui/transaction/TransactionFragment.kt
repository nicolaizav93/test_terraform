package md.endava.internship.budgetplanner.ui.transaction

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentTransactionBinding
import md.endava.internship.budgetplanner.ui.dialog.DataPickerDialogFragment
import md.endava.internship.budgetplanner.ui.dialog.LoadingDialogFragment
import md.endava.internship.budgetplanner.ui.dialog.SuccessTransactionDialogFragment
import md.endava.internship.budgetplanner.utils.Category


class TransactionFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentTransactionBinding
    private val transactionViewModel: TransactionViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CategoryRecyclerViewAdapter

    private var loadingDialog: LoadingDialogFragment = LoadingDialogFragment()
    private var successDialog: SuccessTransactionDialogFragment =
        SuccessTransactionDialogFragment({ onDoneClick() }, { onAddAnotherClick() })

    var dataPicker: DataPickerDialogFragment = DataPickerDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(layoutInflater)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = transactionViewModel
        }

        dataPicker.transactionFragment = this

        initRecycleView()
        setListeners()
        setObservers()
        adapterOnClick(-1)
        transactionViewModel.reset()
        transactionViewModel.updateType("Expense")

        return binding.root
    }

    private fun setObservers() {
        transactionViewModel.categoryList.observe(viewLifecycleOwner) { list ->
            viewAdapter.updateDataSet(list)
        }

        transactionViewModel.errorTitle.observe(viewLifecycleOwner) {
            binding.etTitle.error = it
        }

        transactionViewModel.errorAmount.observe(viewLifecycleOwner) {
            binding.etAmount.error = it
        }

        transactionViewModel.type.observe(viewLifecycleOwner) {
            adapterOnClick(-1)
        }

        transactionViewModel.isValid.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvSave.isClickable = true
                binding.tvSave.alpha = 1.0f
            } else {
                binding.tvSave.isClickable = false
                binding.tvSave.alpha = 0.5f
            }
        }

        transactionViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                loadingDialog.show(
                    childFragmentManager, LoadingDialogFragment.TAG
                )
            } else {
                if (loadingDialog.dialog?.isShowing == true)
                    loadingDialog.dismiss()
            }
        }


        transactionViewModel.showSuccessDialog.observe(viewLifecycleOwner) {
            if (it) {
                successDialog.amount = transactionViewModel.amount.value.toString()
                successDialog.type = transactionViewModel.type.value.toString()
                successDialog.show(
                    childFragmentManager, SuccessTransactionDialogFragment.TAG
                )
            }
        }

    }

    private fun setListeners() {

        binding.rgType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.expense -> transactionViewModel.updateType("Expense")
                R.id.income -> transactionViewModel.updateType("Income")
            }
        }

        binding.tvDate.setOnClickListener {
            if (dataPicker.dialog?.isShowing != true)
                dataPicker.show(
                    childFragmentManager, DataPickerDialogFragment.TAG
                )
        }

        binding.tvSave.setOnClickListener {
            transactionViewModel.save()
        }
    }

    private fun initRecycleView() {

        val layout = FlexboxLayoutManager(activity)
        layout.flexDirection = FlexDirection.ROW
        layout.justifyContent = JustifyContent.FLEX_START

        viewAdapter = CategoryRecyclerViewAdapter(Category.values().copyOfRange(0, 6)) { position ->
            adapterOnClick(position)
        }

        recyclerView = binding.rvCategory.apply {
            layoutManager = layout
            adapter = viewAdapter
        }
    }

    // move to transaction list current
    private fun adapterOnClick(position: Int) {
        transactionViewModel.updateSelectedCategoryId(if (position != -1) viewAdapter.dataSet[position] else null)

        for (i in 0..viewAdapter.itemCount) {
            if (position == -1 || i == position) {
                recyclerView.findViewHolderForAdapterPosition(i)?.itemView?.alpha = 1.0f
            } else {
                recyclerView.findViewHolderForAdapterPosition(i)?.itemView?.alpha = 0.5f
            }
        }
    }

    override fun onDateSet(picker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        transactionViewModel.updateDate(dayOfMonth,month,year)
    }

    private fun onDoneClick() {
        adapterOnClick(-1)
        transactionViewModel.reset()
        findNavController().navigate(R.id.action_transaction_fragment_to_dashboardFragment)
    }

    private fun onAddAnotherClick() {
        adapterOnClick(-1)
        transactionViewModel.reset()
    }

}