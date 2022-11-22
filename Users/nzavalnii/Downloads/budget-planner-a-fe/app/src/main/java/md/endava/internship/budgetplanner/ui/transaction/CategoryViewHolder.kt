package md.endava.internship.budgetplanner.ui.transaction

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.databinding.ItemCategoryBinding
import md.endava.internship.budgetplanner.utils.Category

class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Category) {
        with(binding) {
            tvTitle.text = itemView.context.getString(model.title)
            cvBackground.alpha = 1.0f
            cvBackground.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    model.color
                )
            )
        }
    }
}