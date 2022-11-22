package md.endava.internship.budgetplanner.ui.dashboard

 import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment.ListTransactionByCategoryFragment
import md.endava.internship.budgetplanner.utils.Constants


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): ListTransactionByCategoryFragment {
        val fragment = ListTransactionByCategoryFragment()
        fragment.arguments = Bundle().apply {
            putString(Constants.ARG_TYPE, Constants.types[position])
        }
        return fragment
    }
}