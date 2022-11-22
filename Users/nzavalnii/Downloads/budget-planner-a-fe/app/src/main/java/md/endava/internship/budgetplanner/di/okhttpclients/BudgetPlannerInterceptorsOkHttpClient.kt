package md.endava.internship.budgetplanner.di.okhttpclients

import androidx.databinding.ktx.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.endava.internship.budgetplanner.network.BudgetPlannerInterceptor
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object BudgetPlannerInterceptorsOkHttpClient {

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        okHttpClient: OkHttpClient,
        interceptor: BudgetPlannerInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor { authInterceptor -> interceptor.authIntercept(authInterceptor) }
            .build()
    }

    @EmptyBodyInterceptorOkHttpClient
    @Provides
    fun provideEmptyBodyInterceptorOkHttpClient(
        okHttpClient: OkHttpClient,
        interceptor: BudgetPlannerInterceptor,
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor { auth -> interceptor.authIntercept(auth) }
            .addInterceptor { requestCode ->
                interceptor.emptyResponseIntercept(
                    requestCode
                )
            }
            .build()
    }

    @DefaultInterceptorOkHttpClient
    @Provides
    fun provideDefaultOkHttpClient(
        okHttpClient: OkHttpClient,
        interceptor: BudgetPlannerInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .apply { if (BuildConfig.DEBUG) addInterceptor(interceptor) }
            .build()
    }
}