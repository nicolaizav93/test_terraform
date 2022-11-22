package md.endava.internship.budgetplanner.data.repository.fromapi

import kotlinx.coroutines.CoroutineDispatcher
import md.endava.internship.budgetplanner.data.api.AuthApiService
import md.endava.internship.budgetplanner.data.model.RegistrationPost
import md.endava.internship.budgetplanner.data.model.User
import md.endava.internship.budgetplanner.di.dispatchers.IoDispatcher
import md.endava.internship.budgetplanner.di.retrofit.DefaultRetrofit
import md.endava.internship.budgetplanner.network.ApiCallHandler
import md.endava.internship.budgetplanner.network.Resource
import javax.inject.Inject

class AuthRepository @Inject constructor(
     @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
     @DefaultRetrofit private val defaultAppApi: AuthApiService,
     private val apiCallHandler: ApiCallHandler,
) : AppUser {

    override suspend fun signUp(authorization: RegistrationPost): Resource<Unit> =
        apiCallHandler.safeApiCall(ioDispatcher) { defaultAppApi.registerUser(authorization) }

    override suspend fun signIn(authorization: User): Resource<String> =
        apiCallHandler.safeApiCall(ioDispatcher) {
            defaultAppApi.authoriseUser(
                authorization.login,
                authorization.password
            )
        }
}