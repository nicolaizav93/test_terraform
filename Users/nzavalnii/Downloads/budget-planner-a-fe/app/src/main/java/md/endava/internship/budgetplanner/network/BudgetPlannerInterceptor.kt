package md.endava.internship.budgetplanner.network

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import md.endava.internship.budgetplanner.datastore.DataPreferences
import md.endava.internship.budgetplanner.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BudgetPlannerInterceptor @Inject constructor(
    private val dataStore: DataPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().build()
        )
    }

    fun authIntercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { dataStore.token.first().toString() }
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader(
                    Constants.HEADER_NAME,
                    Constants.AUTHORIZATION_TOKEN_TYPE + token
                )
                .build()
        )
    }

    fun emptyResponseIntercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return if (response.code == 204) response.newBuilder().code(200).build()
        else response
    }
}