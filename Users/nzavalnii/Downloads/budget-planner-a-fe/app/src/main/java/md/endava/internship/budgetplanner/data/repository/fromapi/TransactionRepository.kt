package md.endava.internship.budgetplanner.data.repository.fromapi

import kotlinx.coroutines.CoroutineDispatcher
import md.endava.internship.budgetplanner.data.api.TransactionsApiService
import md.endava.internship.budgetplanner.data.model.transaction.TransactionBodyResponse
import md.endava.internship.budgetplanner.data.model.transaction.TransactionPost
import md.endava.internship.budgetplanner.di.dispatchers.IoDispatcher
import md.endava.internship.budgetplanner.di.retrofit.EmptySuccessRetrofit
import md.endava.internship.budgetplanner.di.retrofit.IsAuthRetrofit
import md.endava.internship.budgetplanner.network.ApiCallHandler
import md.endava.internship.budgetplanner.network.Resource
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @IsAuthRetrofit private val isAuthAppApi: TransactionsApiService,
    @EmptySuccessRetrofit private val withoutResponseApi: TransactionsApiService,
    private val apiCallHandler: ApiCallHandler,
) : TransactionRepositoryInterface {

    override suspend fun getTransactions(): Resource<TransactionBodyResponse> =
        apiCallHandler.safeApiCall(ioDispatcher) {
            isAuthAppApi.getTransactions()
        }

    override suspend fun sendTransaction(args: TransactionPost): Resource<Unit> =
        apiCallHandler.safeApiCall(ioDispatcher) { isAuthAppApi.createTransaction(args) }


    override suspend fun deleteTransaction(id: String): Resource<Unit> =
        apiCallHandler.safeApiCall(ioDispatcher) {
            withoutResponseApi.deleteTransaction(id)
        }

}