package md.endava.internship.budgetplanner.data.model.balance

import com.google.gson.annotations.SerializedName

data class BalanceBodyResponse(

	@field:SerializedName("balance")
	val balance: Double? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
