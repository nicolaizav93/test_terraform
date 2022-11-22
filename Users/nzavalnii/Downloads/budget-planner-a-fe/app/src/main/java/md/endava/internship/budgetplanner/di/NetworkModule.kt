package md.endava.internship.budgetplanner.di

import androidx.databinding.ktx.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import md.endava.internship.budgetplanner.data.api.AuthApiService
import md.endava.internship.budgetplanner.data.api.BalanceService
import md.endava.internship.budgetplanner.data.api.TransactionsApiService
import md.endava.internship.budgetplanner.data.repository.fromapi.AuthRepository
import md.endava.internship.budgetplanner.data.repository.fromapi.BalanceRepository
import md.endava.internship.budgetplanner.data.repository.fromapi.TransactionRepository
import md.endava.internship.budgetplanner.di.dispatchers.IoDispatcher
import md.endava.internship.budgetplanner.di.retrofit.BalanceRetrofit
import md.endava.internship.budgetplanner.di.retrofit.DefaultRetrofit
import md.endava.internship.budgetplanner.di.retrofit.EmptySuccessRetrofit
import md.endava.internship.budgetplanner.di.retrofit.IsAuthRetrofit
import md.endava.internship.budgetplanner.network.ApiCallHandler
import md.endava.internship.budgetplanner.network.BudgetPlannerInterceptor
import md.endava.internship.budgetplanner.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BUDGET_PLANNER_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: BudgetPlannerInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .apply { if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor) }
        .build()

    @Singleton
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }


    @Singleton
    @Provides
    fun provideAuthRepository(
        @DefaultRetrofit isAuthRetrofit: AuthApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        apiCallHandler: ApiCallHandler,
    ): AuthRepository = AuthRepository(ioDispatcher, isAuthRetrofit, apiCallHandler)

    @Singleton
    @Provides
    fun provideTransactionRepository(
        @IsAuthRetrofit authRetrofit: TransactionsApiService,
        @EmptySuccessRetrofit withoutBodyRetrofit: TransactionsApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        apiCallHandler: ApiCallHandler,
    ): TransactionRepository =
        TransactionRepository(ioDispatcher, authRetrofit, withoutBodyRetrofit, apiCallHandler)

    @Singleton
    @Provides
    fun provideBalanceRepository(
        @BalanceRetrofit balanceRetrofit: BalanceService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        apiCallHandler: ApiCallHandler,
    ): BalanceRepository = BalanceRepository(ioDispatcher, balanceRetrofit, apiCallHandler)

}