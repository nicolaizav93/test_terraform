package md.endava.internship.budgetplanner.data.repository.fromapi

import md.endava.internship.budgetplanner.data.model.RegistrationPost
import md.endava.internship.budgetplanner.data.model.User
import md.endava.internship.budgetplanner.network.Resource

interface AppUser {

    suspend fun signUp(authorization: RegistrationPost): Resource<Unit>

    suspend fun signIn(authorization: User): Resource<String>
}