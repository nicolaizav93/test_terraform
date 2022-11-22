package md.endava.internship.budgetplanner.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

typealias RFail = Resource.Failure
typealias RSuccess<T> = Resource.Success<T>

class ApiCallHandler : ProtectedApiInterface {

    override suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                RSuccess(apiCall.invoke())
            } catch (t: Throwable) {
                if (t is HttpException) {
                    RFail(
                        isNetworkError = true,
                        errorCode = t.code(),
                        errorBody = t.response()?.errorBody(),
                        nameThrowable = t.javaClass.name
                    )
                } else {
                    RFail(
                        nameThrowable = t.javaClass.name
                    )
                }
            }
        }
    }


}