package md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.databinding.ItemTransactionCategoryBinding

class TransactionAdapter (
    var dataSet: ArrayList<TransactionModel>,
    private val adapterOnClick: (TransactionModel) -> Unit
) : RecyclerView.Adapter<TransactionViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(dataSet[position])

        holder.itemView.setOnClickListener {
            adapterOnClick(dataSet[position])
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateDataSet(list: ArrayList<TransactionModel>) {
        this.dataSet = list
        notifyDataSetChanged()
    }
}