package md.endava.internship.budgetplanner.data.repository.fromapi

import md.endava.internship.budgetplanner.data.model.balance.BalanceBodyResponse
import md.endava.internship.budgetplanner.network.Resource

interface BalanceRepositoryInterface {

    suspend fun getBalance(): Resource<BalanceBodyResponse>
}