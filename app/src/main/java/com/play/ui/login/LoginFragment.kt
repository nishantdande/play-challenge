package com.play.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.play.utils.Status
import com.play.PlayApplication
import com.play.R
import com.play.ui.base.BaseFragment
import com.play.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlin.reflect.KProperty

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var loginViewModel : LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        changeTheme.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { buttonView, isChecked ->
            if(!changeTheme.isPressed) {
                return@OnCheckedChangeListener;
            }
            loginViewModel.theme(if (isChecked) "ThemeYellow" else "AppTheme")
//            requireActivity().recreate();
            requireActivity().finish()
            val intent = Intent(PlayApplication.applicationContext(), MainActivity::class.java)
            startActivity(intent)
        })

        loginViewModel.userTheme.observe(viewLifecycleOwner, Observer {
            changeTheme.isChecked = it.data.equals("ThemeYellow")
        })
        loginViewModel.getTheme()

        loginViewModel.loginUser.observe(viewLifecycleOwner, Observer {
            if (it.data != null)
                requireActivity().feed_view_pager.currentItem = 1
         });
        loginBtn.setOnClickListener(View.OnClickListener {
//            loginViewModel.login(getText(username), getText(password));
            loginViewModel.login("test@worldofplay.in","Worldofplay@2020");
        })

        usernameEditText.addTextChangedListener(mTextWatcher)
        passwordEditText.addTextChangedListener(mTextWatcher)
        loginViewModel.userValidation.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loginBtn.isEnabled = true
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    it.message?.let { it1 ->
                        showSnackBar(it1);
                        loginBtn.isEnabled = false
                    };
                }
            }
        })
    }

    private fun getText(textInputLayout: TextInputLayout): String {
        return textInputLayout.editText!!.text.toString()
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence,
            i: Int,
            i2: Int,
            i3: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence,
            i: Int,
            i2: Int,
            i3: Int
        ) {
        }

        override fun afterTextChanged(editable: Editable) {
            // check Fields For Empty Values
            loginViewModel.checkValidation(getText(username), getText(password))
        }
    }

}

private operator fun Any.setValue(loginFragment: LoginFragment, property: KProperty<*>, loginViewModel: LoginViewModel) {

}
