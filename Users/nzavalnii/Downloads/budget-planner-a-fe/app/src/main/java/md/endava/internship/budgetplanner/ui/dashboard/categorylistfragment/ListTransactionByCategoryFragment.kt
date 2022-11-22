package md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentListTransactionByCategoryBinding
import md.endava.internship.budgetplanner.ui.dashboard.DashboardViewModel
import md.endava.internship.budgetplanner.utils.Constants

class ListTransactionByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentListTransactionByCategoryBinding

    private val listTransactionByCategoryViewModel: ListTransactionByCategoryViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    private lateinit var viewAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListTransactionByCategoryBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listTransactionByCategoryViewModel
        }

        arguments?.takeIf { it.containsKey(Constants.ARG_TYPE) }?.apply {
            listTransactionByCategoryViewModel.updateType(getString(Constants.ARG_TYPE).toString())
        }

        initRecyclerView()

        setObservers()
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        binding.bAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transaction_fragment)
        }

        binding.ifvAdd.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transaction_fragment)
        }
    }

    private fun setObservers() {
        listTransactionByCategoryViewModel.list.observe(viewLifecycleOwner) {
            viewAdapter.updateDataSet(it)
        }

        if (listTransactionByCategoryViewModel.type.value == "Expense") {
            dashboardViewModel.listExpense.observe(viewLifecycleOwner) {
                listTransactionByCategoryViewModel.loadList(it)
            }
        } else {
            dashboardViewModel.listIncome.observe(viewLifecycleOwner) {
                listTransactionByCategoryViewModel.loadList(it)
            }
        }

        listTransactionByCategoryViewModel.listVisibility.observe(viewLifecycleOwner) {
            if (it) {
                binding.bAddTransaction.isVisible = true
                binding.tvMessage.isVisible = true
                binding.ifvAdd.isVisible = false
            } else {
                binding.bAddTransaction.isVisible = false
                binding.tvMessage.isVisible = false
                binding.ifvAdd.isVisible = true
            }
        }
    }

    private fun initRecyclerView() {
        viewAdapter = TransactionAdapter(arrayListOf()) { model -> adapterOnClick(model) }

        binding.rvCategory.apply {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun adapterOnClick(model: TransactionModel) {
        findNavController().navigate(R.id.action_dashboard_fragment_to_transaction_details_fragment)
    }
}