package md.endava.internship.budgetplanner.utils.validator.patterns

object ValidatorConstants {

    const val EMAIL_VALIDATION_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{3,30}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
            ")+"

    const val PASSWORD_VALIDATION_PATTERN = "^(?=.*[A-Za-z])(?=.*[!#\$%&'( )*+,-./:;<=>?@[\\]^_`{|}~]])(?=.*[A-Za-z\\d!#\$%&'( )*+,-./:;<=>?@[\\]^_`{|}~]]).{8,22}"
    const val NAME_ALPHANUMERIC_PATTERN = "^[A-Z][a-z]+\$"

    const val NAME_RANGE_START = 3

    const val NAME_RANGE_END = 22
}