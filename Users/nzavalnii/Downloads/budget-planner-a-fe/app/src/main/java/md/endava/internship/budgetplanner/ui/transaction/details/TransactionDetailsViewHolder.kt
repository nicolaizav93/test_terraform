package md.endava.internship.budgetplanner.ui.transaction.details

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.databinding.TransactionDetailHolderDeleteItemBinding
import java.lang.ref.WeakReference

class TransactionDetailsViewHolder(
    private val binding: TransactionDetailHolderDeleteItemBinding
) : RecyclerView.ViewHolder(binding.itemDetail) {

    private val view = WeakReference(binding.itemDetail)

    private var subRectangle: FrameLayout

    var onDeleteClickItem: ((FrameLayout) -> Unit)? = null

    init {

        view.get()?.let {
            it.setOnClickListener {

                if (view.get()?.scrollX != 0) {
                    view.get()?.scrollTo(0, 0)
                }

            }
        }

        subRectangle = binding.subRectangleOnDelete

        subRectangle.let {
            it.setOnClickListener {
                onDeleteClickItem?.let { onDeleteClickItem ->
                    onDeleteClickItem(it as FrameLayout)
                }
            }
        }

    }

    fun onBindTransaction() {
        binding.bottomTextDate.text = "16/03/2021"
        binding.leftTextAmount.text = "$14.45"
        binding.topTextTransaction.text = "McDonalds"
    }

}