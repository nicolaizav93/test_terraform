package md.endava.internship.budgetplanner.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentDashboardBinding
import md.endava.internship.budgetplanner.utils.Constants
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var chart: BudgetChart

    private lateinit var binding: FragmentDashboardBinding

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = dashboardViewModel
        }

        dashboardViewModel.loadTransaction()
        dashboardViewModel.loadBalance()
        chart.chart = binding.pcData

        chart.initDefaultChart()

        setViewPager()

        setListeners()
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        dashboardViewModel.showChart.observe(viewLifecycleOwner) {
            if (it) {
                binding.vCard.visibility = View.VISIBLE
                binding.pcData.visibility = View.GONE
            } else {
                binding.vCard.visibility = View.INVISIBLE
                binding.pcData.visibility = View.VISIBLE
            }
        }

        dashboardViewModel.chartList.observe(viewLifecycleOwner) { newList ->
            chart.updateChartData(
                dashboardViewModel.chartColor.value,
                dashboardViewModel.centerText.value,
                newList,
                dashboardViewModel.showChart.value == false
            )
        }

        dashboardViewModel.centerText.observe(viewLifecycleOwner) {
            binding.vCard.alpha = if (it == "$0") 0.5f else 1f
        }
    }

    private fun setListeners() {

        binding.vpCategory.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dashboardViewModel.setType(Constants.types[position])
                if (position == 0) {
                    binding.vCard.setBackgroundResource(R.drawable.background_card_expenses)
                } else {
                    binding.vCard.setBackgroundResource(R.drawable.background_card_income)
                }
                super.onPageSelected(position)
            }
        })

        binding.ibChange.setOnClickListener {
            dashboardViewModel.updateDataShowerType()
        }

        binding.tvSignOut.setOnClickListener {
            dashboardViewModel.signOut()
            findNavController().navigate(R.id.action_dashboardFragment_to_splash_fragment)
        }
    }

    private fun setViewPager() {
        binding.vpCategory.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tlTabs, binding.vpCategory) { tab, position ->
            tab.text = Constants.types[position]
        }.attach()
    }
}