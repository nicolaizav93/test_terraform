package md.endava.internship.budgetplanner.utils.validator.model

enum class ValidatorStates(var isValid: Pair<String, Boolean>) {
    NAME("ALPHANUMERIC" to false),
    EMAIL("EMAIL" to false),
    PASSWORD("PASSWORD" to false)
}
