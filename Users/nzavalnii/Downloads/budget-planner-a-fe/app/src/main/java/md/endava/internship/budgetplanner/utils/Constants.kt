package md.endava.internship.budgetplanner.utils

import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.ui.transaction.CategoryModel

import md.endava.internship.budgetplanner.BuildConfig
import md.endava.internship.budgetplanner.ui.dashboard.categorylistfragment.TransactionModel

object Constants {

    private const val API = "/api/"
    private const val API_ACCOUNT = "${API}Account/"

    const val ACCOUNT_LOGIN = "${API_ACCOUNT}login"

    const val BUDGET_PLANNER_API: String = BuildConfig.BUDGET_PLANNER_API_URL
    const val HEADER_NAME = "Authorization"
    const val AUTHORIZATION_TOKEN_TYPE = "Bearer "

    const val LOGIN = "Login"
    const val PASSWORD = "Password"
    const val TRANSACTION = "${API}transactions"
    const val TYPE = "type"
    const val CATEGORY = "category"
    const val LIMIT = "limit"
    const val OFFSET = "offset"
    const val FROM_DATE = "fromDate"
    const val TO_DATE = "toDate"
    const val REGISTRATION = "${API_ACCOUNT}registration"

    const val TRANSACTION_ID = "transactionId"

    const val BALANCE = "${API}Balance"

    const val error_login =  "Entered credentials are invalid!"

    val types = arrayOf("Expense", "Income")
    const val ARG_TYPE = "type"

    const val TRANSACTION_DETAIL_DELETE_VIEW_WIDTH = 41.0f


    val defaultArrayExpense = arrayListOf(
        TransactionModel(Category.FOOD_AND_DRINK, 0, 0.0),
        TransactionModel(Category.ENTERTAINMENT, 0, 0.0),
        TransactionModel(Category.TAXES_AND_PAYMENTS, 0, 0.0),
        TransactionModel(Category.SHOPPING, 0, 0.0),
        TransactionModel(Category.SPORTS, 0, 0.0),
        TransactionModel(Category.OTHERS_EXPENSE, 0, 0.0),
    )

    val defaultArrayIncome = arrayListOf(
        TransactionModel(Category.SALARIES_AND_BONUSES, 0, 0.0),
        TransactionModel(Category.INVESTMENTS, 0, 0.0),
        TransactionModel(Category.PAYMENTS_AND_OTHERS, 0, 0.0),
        TransactionModel(Category.OTHERS_INCOME, 0, 0.0),
    )
}