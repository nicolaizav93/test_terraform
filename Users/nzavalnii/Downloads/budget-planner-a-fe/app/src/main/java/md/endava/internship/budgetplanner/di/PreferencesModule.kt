package md.endava.internship.budgetplanner.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.datastore.DataPreferences
import md.endava.internship.budgetplanner.datastore.DefaultDataPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDefaultDataPreferences(@ApplicationContext appContext: Context): DataPreferences =
        DefaultDataPreferences(appContext)
}