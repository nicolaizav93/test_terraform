package md.endava.internship.budgetplanner.data.repository.fromapi

import kotlinx.coroutines.CoroutineDispatcher
import md.endava.internship.budgetplanner.data.api.BalanceService
import md.endava.internship.budgetplanner.data.model.balance.BalanceBodyResponse
import md.endava.internship.budgetplanner.di.dispatchers.IoDispatcher
import md.endava.internship.budgetplanner.di.retrofit.BalanceRetrofit
import md.endava.internship.budgetplanner.network.ApiCallHandler
import md.endava.internship.budgetplanner.network.Resource
import javax.inject.Inject

class BalanceRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @BalanceRetrofit private val isAuthAppApi: BalanceService,
    private val apiCallHandler: ApiCallHandler,
) : BalanceRepositoryInterface {

    override suspend fun getBalance(): Resource<BalanceBodyResponse> =
        apiCallHandler.safeApiCall(ioDispatcher) { isAuthAppApi.getBalance() }

}