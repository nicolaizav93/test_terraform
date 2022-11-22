package md.endava.internship.budgetplanner.data.repository.fromapi

import md.endava.internship.budgetplanner.data.model.transaction.TransactionBodyResponse
import md.endava.internship.budgetplanner.data.model.transaction.TransactionPost
import md.endava.internship.budgetplanner.network.Resource

interface TransactionRepositoryInterface {

    suspend fun sendTransaction(args: TransactionPost): Resource<Unit>

    suspend fun deleteTransaction(id: String): Resource<Unit>

    suspend fun getTransactions(): Resource<TransactionBodyResponse>

}