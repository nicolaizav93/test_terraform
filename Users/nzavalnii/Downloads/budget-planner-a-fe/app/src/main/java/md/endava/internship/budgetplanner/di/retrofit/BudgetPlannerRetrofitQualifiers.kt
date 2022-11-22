package md.endava.internship.budgetplanner.di.retrofit

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IsAuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmptySuccessRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BalanceRetrofit