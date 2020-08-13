package com.play.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.play.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlin.reflect.KProperty

@AndroidEntryPoint
class LoginFragment : Fragment() {

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
        loginBtn.setOnClickListener(View.OnClickListener {
            //mainViewModel.login("test@worldofplay.in","Worldofplay@2020");
        })
//
//        mainViewModel.loginUser.observe(viewLifecycleOwner, Observer {
//            if (it.data != null)
//                Log.d("Login", it.data.accessToken);
//         });
    }

}

private operator fun Any.setValue(loginFragment: LoginFragment, property: KProperty<*>, loginViewModel: LoginViewModel) {

}
