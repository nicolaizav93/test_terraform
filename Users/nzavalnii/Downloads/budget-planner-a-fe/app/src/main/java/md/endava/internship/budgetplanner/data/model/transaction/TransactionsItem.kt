package md.endava.internship.budgetplanner.data.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionsItem(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("note")
    val note: String? = null,

    @field:SerializedName("amount")
    val amount: Int? = null,

    @field:SerializedName("createUtcDate")
    val createUtcDate: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null
)