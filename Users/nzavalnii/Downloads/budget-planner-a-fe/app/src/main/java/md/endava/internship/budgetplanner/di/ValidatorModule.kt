package md.endava.internship.budgetplanner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.utils.validator.AppValidator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidatorModule {

    @Provides
    @Singleton
    fun provideAppValidator() = AppValidator()
}