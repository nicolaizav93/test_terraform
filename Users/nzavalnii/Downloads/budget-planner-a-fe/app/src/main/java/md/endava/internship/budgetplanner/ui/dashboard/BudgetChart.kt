package md.endava.internship.budgetplanner.ui.dashboard

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import md.endava.internship.budgetplanner.R


class BudgetChart {
    var chart: PieChart? = null

    fun initDefaultChart() {
        chart?.apply {

            val date = arrayListOf<PieEntry>()
            date.add(PieEntry(1F))

            val colors = ArrayList<Int>()
            colors.add(ContextCompat.getColor(context, R.color.text_hide_color))

            val dateSet = PieDataSet(date, "")
            dateSet.colors = colors

            data = PieData(dateSet)

            data.setDrawValues(false)
            centerText = "$00.00"
            setCenterTextColor(Color.WHITE)
            setCenterTextSize(18F)
            setCenterTextOffset(0f, 3f)
            legend.isEnabled = false
            holeRadius = 50f

            description.text = ""
            setHoleColor(Color.TRANSPARENT)
            setTransparentCircleColor(Color.TRANSPARENT)
            setTransparentCircleAlpha(0)
            setDrawCenterText(true)
            rotationAngle = 0F
            isRotationEnabled = true
            isClickable = false

            invalidate()
        }
    }

    fun updateChartData(
        col: ArrayList<Int>?,
        text: String?,
        list: ArrayList<PieEntry>,
        isShow: Boolean
    ) {
        chart?.apply {
            val colors = ArrayList<Int>()
            if (col != null) for (c in col) {
                colors.add(ContextCompat.getColor(context, c))
            }

            val dateSet = PieDataSet(list, "")
            dateSet.colors = colors
            centerText = text
            data = PieData(dateSet)
            data.setDrawValues(false)
            visibility = View.GONE
            if (isShow) {
                visibility = View.VISIBLE
            }
        }
    }
}