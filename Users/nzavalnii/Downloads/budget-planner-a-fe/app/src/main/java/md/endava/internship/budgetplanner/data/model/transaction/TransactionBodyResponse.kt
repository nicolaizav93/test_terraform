package md.endava.internship.budgetplanner.data.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionBodyResponse(

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("totalMatchingRecords")
	val totalMatchingRecords: Int? = null,

	@field:SerializedName("transactions")
	val transactions: List<TransactionsItem?>? = null
)


