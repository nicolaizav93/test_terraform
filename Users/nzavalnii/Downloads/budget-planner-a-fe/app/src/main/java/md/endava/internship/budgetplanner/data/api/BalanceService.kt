package md.endava.internship.budgetplanner.data.api

import md.endava.internship.budgetplanner.data.model.balance.BalanceBodyResponse
import md.endava.internship.budgetplanner.utils.Constants
import retrofit2.http.GET

interface BalanceService {
    @GET(Constants.BALANCE)
    suspend fun getBalance(): BalanceBodyResponse
}