package md.endava.internship.budgetplanner.utils.validator


interface AppValidatorInterface {

    fun validateEmail(inputEmail: String): Boolean

    fun validateName(inputName: String): Map<String, Boolean>

    fun validatePassword(inputPass: String, sameInputPass: String): Boolean

}