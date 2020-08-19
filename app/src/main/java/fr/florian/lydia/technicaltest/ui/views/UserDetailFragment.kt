package fr.florian.lydia.technicaltest.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.databinding.FragmentUserDetailBinding
import fr.florian.lydia.technicaltest.ui.viewmodels.UserViewModel

class UserDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userViewModel = UserViewModel(UserDetailFragmentArgs.fromBundle(it).user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_detail,
            container,
            false
        )
        binding.user = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}