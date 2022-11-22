package md.endava.internship.budgetplanner.data.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionPost(
    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("note")
    val note: String? = null,

    @field:SerializedName("amount")
    val amount: Double? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("type")
    val type: String? = null
)