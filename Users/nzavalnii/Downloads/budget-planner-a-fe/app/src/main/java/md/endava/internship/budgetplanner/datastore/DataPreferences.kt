package md.endava.internship.budgetplanner.datastore

import kotlinx.coroutines.flow.Flow

interface DataPreferences {
    val token: Flow<String?>
    suspend fun saveToken(token: String)
}