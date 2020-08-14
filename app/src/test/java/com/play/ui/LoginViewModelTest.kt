package com.play.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.play.data.api.ApiHelper
import com.play.data.model.LoginResponse
import com.play.data.repository.APIRepository
import com.play.ui.login.LoginViewModel
import com.play.utils.NetworkHelper
import com.play.utils.Resource
import com.play.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiRepository: APIRepository

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var loginUsersObserver: Observer<Resource<LoginResponse>>

    @Mock
    private lateinit var validationObserver: Observer<Resource<String>>

    private val EMAIL_ADDRESS = "test@worldofplay.in"
    private val PASSWORD = "Worldofplay@2020"
    private val EMPTY_FIELD = ""
    private val INVALID_EMAIL = "testworldofplay.in"
    private val INVALID_PASSWORD = "worldofplay2020"

    @Test
    fun testLoginParameter() {
        testCoroutineRule.runBlockingTest {
            val viewModel = LoginViewModel(apiRepository, networkHelper);
            viewModel.userValidation.observeForever(validationObserver)
            viewModel.checkValidation(EMAIL_ADDRESS, PASSWORD);

            Mockito.verify(validationObserver).onChanged(Resource.success("true"))
            viewModel.userValidation.removeObserver(validationObserver)
        }
    }


    @Test
    fun testLoginEmptyEmail() {
        testCoroutineRule.runBlockingTest {
            val viewModel = LoginViewModel(apiRepository, networkHelper);
            viewModel.userValidation.observeForever(validationObserver)
            viewModel.checkValidation(EMPTY_FIELD, PASSWORD);

            Mockito.verify(validationObserver).onChanged(Resource.error("Please provide a email.", null))
            viewModel.userValidation.removeObserver(validationObserver)
        }
    }

    @Test
    fun testLoginEmptyPassword() {
        testCoroutineRule.runBlockingTest {
            val viewModel = LoginViewModel(apiRepository, networkHelper);
            viewModel.userValidation.observeForever(validationObserver)
            viewModel.checkValidation(EMAIL_ADDRESS, EMPTY_FIELD);

            Mockito.verify(validationObserver).onChanged(Resource.error("Please provide a password.", null))
            viewModel.userValidation.removeObserver(validationObserver)
        }
    }


    @Test
    fun testLogin() {
        testCoroutineRule.runBlockingTest {

        }
    }
}

