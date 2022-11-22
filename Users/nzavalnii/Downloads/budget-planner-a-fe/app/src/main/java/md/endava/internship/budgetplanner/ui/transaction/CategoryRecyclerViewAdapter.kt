package md.endava.internship.budgetplanner.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.databinding.ItemCategoryBinding
import md.endava.internship.budgetplanner.utils.Category

class CategoryRecyclerViewAdapter(
    var dataSet: Array<Category>,
    private val adapterOnClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    var selectedPos: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(dataSet[position])

        holder.itemView.setOnClickListener {
            selectedPos = if (selectedPos == position) -1 else position

            adapterOnClick(selectedPos)
        }

    }

    override fun getItemCount(): Int = dataSet.size

    fun updateDataSet(list: Array<Category>) {
        this.dataSet = list
        notifyDataSetChanged()
    }

}