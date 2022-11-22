package md.endava.internship.budgetplanner.di.chart

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.ui.dashboard.BudgetChart
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ChartModule {
    @Singleton
    @Provides
    fun provideBudgetChart(): BudgetChart = BudgetChart()
}