package md.endava.internship.budgetplanner.utils.validator

import md.endava.internship.budgetplanner.utils.validator.patterns.ValidatorConstants

class AppValidator : AppValidatorInterface {

    override fun validateEmail(inputEmail: String) =
        validateInput(inputEmail, ValidatorConstants.EMAIL_VALIDATION_PATTERN)


    override fun validateName(inputName: String): MutableMap<String, Boolean> {
        val validateNameMap = mutableMapOf<String, Boolean>()
        val isValid = validateInput(inputName, ValidatorConstants.NAME_ALPHANUMERIC_PATTERN)
        val inRange = inRange(
            inputName,
            ValidatorConstants.NAME_RANGE_START,
            ValidatorConstants.NAME_RANGE_END
        )
        validateNameMap["isValid"] = isValid
        validateNameMap["inRange"] = inRange
        return validateNameMap
    }

    override fun validatePassword(inputPass: String, sameInputPass: String): Boolean {
        return if (inputPass == sameInputPass)
            validateInput(inputPass, ValidatorConstants.PASSWORD_VALIDATION_PATTERN)
        else false
    }

    private fun validateInput(str: String, pattern: String) = Regex(pattern).containsMatchIn(str)

    private fun inRange(str: String, min: Int, max: Int) = str.length in min..max
}