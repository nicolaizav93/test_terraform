package md.endava.internship.budgetplanner.ui.splashscreen

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val splashViewModel: SplashViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        splashViewModel.getToken()

        setObserver()

        return binding.root
    }

    private fun setObserver() {
        splashViewModel.isValidToken.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_splash_fragment_to_dashboardFragment)
            } else {
                findNavController().navigate(R.id.action_splash_fragment_to_registration_part_one_fragment)
            }
        }
    }
}