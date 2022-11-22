package md.endava.internship.budgetplanner.utils

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import md.endava.internship.budgetplanner.R

enum class Category(
    @StringRes var title: Int,
    @ColorRes var color: Int,
    @DrawableRes var icon: Int,
    var key: String
) {
    FOOD_AND_DRINK(
        R.string.food_and_drink_category,
        R.color.blue,
        R.drawable.ic_food_and_drink,
        "FoodAndDrink"
    ),
    ENTERTAINMENT(
        R.string.entertainment_category,
        R.color.purple,
        R.drawable.ic_entertaiment,
        "Entertainment"
    ),
    TAXES_AND_PAYMENTS(
        R.string.taxes_and_paymnets_category,
        R.color.yellow,
        R.drawable.ic_taxes_and_payments,
        "TaxesAndPayments"
    ),
    SHOPPING(R.string.shopping_cateogry, R.color.pink, R.drawable.ic_shopping, "Shopping"),
    SPORTS(R.string.sports_category, R.color.orange, R.drawable.ic_sports, "Sports"),
    OTHERS_EXPENSE(R.string.others_category, R.color.green, R.drawable.ic_other, "Others"),
    SALARIES_AND_BONUSES(
        R.string.salaries_and_bonuses_category,
        R.color.light_orange,
        R.drawable.ic_salaries_and_bonuses,
        "SalariesAndBonuses"
    ),
    INVESTMENTS(
        R.string.investments_category,
        R.color.dark_blue,
        R.drawable.ic_investments,
        "Investments"
    ),
    PAYMENTS_AND_OTHERS(
        R.string.payments_and_others_category,
        R.color.light_blue,
        R.drawable.ic_payments_and_transfers,
        "PaymentsAndOther"
    ),
    OTHERS_INCOME(R.string.others_category, R.color.green, R.drawable.ic_other, "Others")
}