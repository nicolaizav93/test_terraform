package md.endava.internship.budgetplanner.di.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.data.api.AuthApiService
import md.endava.internship.budgetplanner.data.api.BalanceService
import md.endava.internship.budgetplanner.data.api.TransactionsApiService
import md.endava.internship.budgetplanner.di.okhttpclients.AuthInterceptorOkHttpClient
import md.endava.internship.budgetplanner.di.okhttpclients.EmptyBodyInterceptorOkHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object BudgetPlannerRetrofit {

    @IsAuthRetrofit
    @Provides
    fun provideAuthRetrofit(
        retrofit: Retrofit,
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): TransactionsApiService = retrofit.newBuilder().client(okHttpClient).build()
        .create(TransactionsApiService::class.java)

    @EmptySuccessRetrofit
    @Provides
    fun provideWithoutBodyRetrofit(
        retrofit: Retrofit,
        @EmptyBodyInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): TransactionsApiService = retrofit.newBuilder().client(okHttpClient).build()
        .create(TransactionsApiService::class.java)

    @BalanceRetrofit
    @Provides
    fun provideBalanceRetrofit(
        retrofit: Retrofit,
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): BalanceService = retrofit.newBuilder().client(okHttpClient).build()
        .create(BalanceService::class.java)

    @DefaultRetrofit
    @Provides
    fun provideDefaultRetrofit(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient
    ): AuthApiService = retrofit.newBuilder().client(okHttpClient).build()
        .create(AuthApiService::class.java)


}