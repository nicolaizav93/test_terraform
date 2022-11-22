package md.endava.internship.budgetplanner.ui.transaction.details

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import md.endava.internship.budgetplanner.utils.Constants.TRANSACTION_DETAIL_DELETE_VIEW_WIDTH

class TransactionDetailItemTouchHelper(transactionDetailItem: TransactionDetailsFragment) :
    ItemTouchHelper.Callback() {

    private val limitScrollX = dipToPx(TRANSACTION_DETAIL_DELETE_VIEW_WIDTH, transactionDetailItem)
    private var currentScrollX = 0
    private var currentScrollWhenActive = 0
    private var initXWhenInActive = 0f
    private var firstInActive = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        val dragFlags = 0
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return Integer.MAX_VALUE.toFloat()
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return Integer.MAX_VALUE.toFloat()
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            if (dX == 0f) {
                currentScrollX = viewHolder.itemView.scrollX
                firstInActive = true
            }

            if (isCurrentlyActive) {

                var scrollOffSet = currentScrollX + (-dX).toInt()
                if (scrollOffSet > limitScrollX) {
                    scrollOffSet = limitScrollX
                } else if (scrollOffSet < 0) {
                    scrollOffSet = 0
                }

                viewHolder.itemView.scrollTo(scrollOffSet, 0)
            } else {

                if (firstInActive) {
                    firstInActive = false
                    currentScrollWhenActive = viewHolder.itemView.scrollX
                    initXWhenInActive = dX
                }

                if (viewHolder.itemView.scrollX < limitScrollX) {
                    viewHolder.itemView.scrollTo(
                        (currentScrollWhenActive * dX / initXWhenInActive).toInt(),
                        0
                    )
                }

            }
        }
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {

        super.clearView(recyclerView, viewHolder)

        if (viewHolder.itemView.scrollX < 0) {
            viewHolder.itemView.scrollTo(0, 0)
        } else if (viewHolder.itemView.scrollX < 0) {
            viewHolder.itemView.scrollTo(0, 0)
        }

    }

    private fun dipToPx(dipValue: Float, itemView: TransactionDetailsFragment): Int {
        return (dipValue * itemView.resources.displayMetrics.density).toInt()
    }
}