package md.endava.internship.budgetplanner.utils

import androidx.annotation.StringRes
import md.endava.internship.budgetplanner.R

enum class Screen(@StringRes val label: Int, val topBarVisible: Boolean) {
    SPLASH(R.string.fragment_splash_label, false),
    LOGIN(R.string.fragment_log_in_label, false),
    REGISTRATION_ONE(R.string.fragment_registration_part_one_label, false),
    REGISTRATION_TWO(R.string.fragment_registration_part_two_label,true),
    REGISTRATION_THREE(R.string.fragment_registration_part_three_label,true),
    WELCOME(R.string.fragment_welcome_screen_label,false),
    TRANSACTION(R.string.fragment_transaction_label,true),

}