package md.endava.internship.budgetplanner.di.handler

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.network.ApiCallHandler
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HandlerApiCallModule {

    @Singleton
    @Provides
    fun provideHandlerApiCall(): ApiCallHandler = ApiCallHandler()
}