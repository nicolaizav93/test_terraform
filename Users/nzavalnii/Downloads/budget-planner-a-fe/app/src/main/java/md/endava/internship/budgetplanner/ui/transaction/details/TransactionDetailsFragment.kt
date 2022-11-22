package md.endava.internship.budgetplanner.ui.transaction.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import md.endava.internship.budgetplanner.databinding.FragmentTransactionDetailsBinding

class TransactionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = mutableListOf<Int>()
        for (i in 0 until 5) {
            list.add(i)
        }

        binding.recyclerViewTransactionDetail.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = TransactionDetailsAdapter(list)
        }
        val itemSwipe = TransactionDetailItemTouchHelper(this@TransactionDetailsFragment)

        ItemTouchHelper(itemSwipe).attachToRecyclerView(binding.recyclerViewTransactionDetail)
    }
}