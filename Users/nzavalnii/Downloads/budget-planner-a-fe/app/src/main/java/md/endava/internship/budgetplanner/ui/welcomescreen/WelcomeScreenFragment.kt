package md.endava.internship.budgetplanner.ui.welcomescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import md.endava.internship.budgetplanner.R
import md.endava.internship.budgetplanner.databinding.FragmentWelcomeScreenBinding


class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeScreenBinding.inflate(layoutInflater, container, false)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_screen_fragment_to_log_in_fragment)
        }

        return binding.root
    }

}