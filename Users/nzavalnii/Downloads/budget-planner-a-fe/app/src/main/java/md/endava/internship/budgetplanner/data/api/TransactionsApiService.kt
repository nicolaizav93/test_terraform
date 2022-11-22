package md.endava.internship.budgetplanner.data.api

import md.endava.internship.budgetplanner.data.model.transaction.TransactionBodyResponse
import md.endava.internship.budgetplanner.data.model.transaction.TransactionPost
import md.endava.internship.budgetplanner.utils.Constants
import retrofit2.http.*

interface TransactionsApiService {


    @POST(Constants.TRANSACTION)
    suspend fun createTransaction(@Body body: TransactionPost)

    @DELETE(Constants.TRANSACTION)
    suspend fun deleteTransaction(@Query(Constants.TRANSACTION_ID) transactId: String)

    @GET(Constants.TRANSACTION)
    suspend fun getTransactions(
        @Query(Constants.TYPE) type: String? = null,
        @Query(Constants.CATEGORY) category: String? = null,
        @Query(Constants.LIMIT) limit: Int? = null,
        @Query(Constants.OFFSET) offset: Int? = null,
        @Query(Constants.FROM_DATE) startDate: String? = null,
        @Query(Constants.TO_DATE) endDate: String? = null
    ): TransactionBodyResponse
}