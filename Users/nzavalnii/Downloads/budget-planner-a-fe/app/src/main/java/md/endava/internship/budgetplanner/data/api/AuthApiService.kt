package md.endava.internship.budgetplanner.data.api

import md.endava.internship.budgetplanner.data.model.RegistrationPost
import md.endava.internship.budgetplanner.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @GET(Constants.ACCOUNT_LOGIN)
    suspend fun authoriseUser(
        @Query(Constants.LOGIN) login: String,
        @Query(Constants.PASSWORD) password: String
    ): String

    @POST(Constants.REGISTRATION)
    suspend fun registerUser(@Body body: RegistrationPost)
}