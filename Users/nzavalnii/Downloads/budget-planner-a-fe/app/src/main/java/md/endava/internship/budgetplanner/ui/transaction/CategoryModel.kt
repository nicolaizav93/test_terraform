package md.endava.internship.budgetplanner.ui.transaction

import androidx.annotation.ColorRes

data class CategoryModel(
    var title: String,
    @ColorRes var color: Int,
    var key: String
){
    override fun toString(): String {
        return title
    }
}