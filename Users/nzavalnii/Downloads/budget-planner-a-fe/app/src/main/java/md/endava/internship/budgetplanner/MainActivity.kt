package md.endava.internship.budgetplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import md.endava.internship.budgetplanner.utils.Screen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.app_bar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            val showBottomNav = Screen.values()
                .firstOrNull { getString(it.label) == destination.label }?.topBarVisible

            toolbar.isVisible = showBottomNav == true
        }
    }

}