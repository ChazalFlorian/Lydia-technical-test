package fr.florian.lydia.technicaltest.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import fr.florian.lydia.technicaltest.R
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

    @SuppressLint("ClickableViewAccessibility")
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

        binding.showPasswordCta.setOnTouchListener { view, me ->
            if (me.action == MotionEvent.ACTION_DOWN) {

                val text = binding.passwordText.text
                binding.passwordText.setText("")
                binding.passwordText.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
                binding.passwordText.text = text
                (view as AppCompatButton).text = view.resources.getText(R.string.icon_slashed_eyes)

            } else if (me.action == MotionEvent.ACTION_UP) {

                val text = binding.passwordText.text
                binding.passwordText.setText("")
                binding.passwordText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordText.text = text

                (view as AppCompatButton).text = view.resources.getText(R.string.icon_open_eyes)
            }
            true
        }

        return binding.root
    }
}