package md.endava.internship.budgetplanner.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean? = null,
        val errorCode: Int? = null,
        val errorBody: ResponseBody? = null,
        val nameThrowable: String? = null,
    ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}