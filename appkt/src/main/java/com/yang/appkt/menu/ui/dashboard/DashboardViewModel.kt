package com.yang.appkt.menu.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yang.appkt.menu.bean.LoginData
class DashboardViewModel : ViewModel() {

    var login = MutableLiveData(LoginData("zhangSan",12,"boy"))


}