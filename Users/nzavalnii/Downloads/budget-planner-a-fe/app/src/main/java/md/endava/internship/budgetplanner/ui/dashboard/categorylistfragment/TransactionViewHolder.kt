package md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.ItemTransactionCategoryBinding

class TransactionViewHolder(
    private val binding: ItemTransactionCategoryBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: TransactionModel) {
        with(binding) {
            tvCategory.text = itemView.context.getString(model.category.title)
            cvIcon.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    model.category.color
                )
            )
            ivIcon.setBackgroundResource(model.category.icon)
            tvCount.text = itemView.context.getString(R.string.transaction_count, model.count.toString())
        }
    }
}