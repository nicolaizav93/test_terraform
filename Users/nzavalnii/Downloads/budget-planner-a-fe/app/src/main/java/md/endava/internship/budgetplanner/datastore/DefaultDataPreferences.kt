package md.endava.internship.budgetplanner.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")

@Singleton
class DefaultDataPreferences @Inject constructor(
    context: Context
) : DataPreferences {
    private val applicationContext = context.applicationContext

    override val token: Flow<String?>
        get() = applicationContext.dataStore.data.map { datastore ->
            datastore[TOKEN] ?: ""
        }

    override suspend fun saveToken(token: String) {
        applicationContext.dataStore.edit { datastore ->
            datastore[TOKEN] = token
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("token")
    }
}