package md.endava.internship.budgetplanner.di.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GsonModule {
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()
}