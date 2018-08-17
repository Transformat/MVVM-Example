package self.mvvmexample.modules.loginModule

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import bridgingtech.denefits.modules.baseModule.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import self.mvvmexample.R
import self.mvvmexample.databinding.ActivityLoginBinding
import self.mvvmexample.utils.DialogPopup
import self.mvvmexample.utils.Utility

class LoginActivity : BaseActivity() {


    var loginViewModel: LoginViewModel? = null
    var loginDataBinding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        initBindings()
        super.onCreate(savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        setupSnackBar(parentLayout)
    }


    private fun initBindings() {
        //Binding the layout with data binder
        loginDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        //Initializing the viewModel for this Activity
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        //Binding the view model with the DataBinding
        loginDataBinding?.loginData = loginViewModel
        //Making binding lifecycleAware
        loginDataBinding?.setLifecycleOwner(this)
        //Making binding set all the existing data
        loginDataBinding?.executePendingBindings()
    }

    private fun initObservers() {

        //Observing loader live data and showing and hiding loader
        loginViewModel?.loader?.observe(this, Observer {
            Utility.hideKeyboard(this, emailEt)
            if (it!!) {
                DialogPopup.showLoadingDialog(this, getString(R.string.loading))
            } else {
                DialogPopup.dismissLoadingDialog()
            }
        })

        //Showing a snack bar on error by observing the snack bar live data
        loginViewModel?.showSnackBarFieldError?.observe(this, Observer { value ->
            Utility.hideKeyboard(this, emailEt)
            snackBar?.setText(value!!.first)
            snackBar?.show()
            when (value!!.second) {
                LoginViewModel.LoginFields.EMAIL.value -> {
                    emailEt.requestFocus()
                }
                LoginViewModel.LoginFields.PASSWORD.value -> {
                    passwordEt.requestFocus()
                }
            }
        })

    }
}