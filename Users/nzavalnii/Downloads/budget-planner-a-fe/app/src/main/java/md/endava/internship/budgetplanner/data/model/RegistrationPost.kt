package md.endava.internship.budgetplanner.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationPost(

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("industry")
    val industry: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("passwordConfirmation")
    val passwordConfirmation: String? = null,

    @field:SerializedName("initialAmount")
    val initialAmount: Double? = null,
)
