package md.endava.internship.budgetplanner.ui.transaction.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.R

class TransactionDetailsAdapter(
    private val list: MutableList<Int> = mutableListOf()
) : RecyclerView.Adapter<TransactionDetailsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionDetailsViewHolder {
        return TransactionDetailsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.transaction_detail_holder_delete_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionDetailsViewHolder, position: Int) {
        holder.onBindTransaction()
    }

    override fun getItemCount(): Int = list.size
}