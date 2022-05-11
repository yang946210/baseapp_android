package com.yang.appkt.menu.ui.dashboard

import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DashboardViewModel : ViewModel() {

    private var _login = MutableLiveData(LoginData("zhangSan",12,"boy"))

    val login:LiveData<LoginData> get() = _login

    fun changeData(data:LoginData){
        _login.value=data
    }

    fun onViewClick(view: TextView){
        Toast.makeText(view.context,"lalala",Toast.LENGTH_LONG).show()
    }

}

data class LoginData(var name:String, var age:Int,var info:String)