package com.yang.appkt.menu

import android.os.Bundle
import com.yang.appkt.databinding.ActivityRoomBinding
import com.yang.appkt.room.AppDataBase
import com.yang.appkt.room.dao.UserDao
import com.yang.appkt.room.entity.User
import com.yang.appkt.viewmodel.RoomViewModel
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.ext.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class RoomActivity: BaseActivity<RoomViewModel, ActivityRoomBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        Thread.currentThread().toString().logD()
        binding.apply {
            tvDelete.setOnClickListener {
                runBlocking {
                    AppDataBase.getDatabase(this@RoomActivity).getUserDao().deleteUserByName("li")
                }

            }
            tvInsert.setOnClickListener {
                runBlocking (Dispatchers.IO){
                    Thread.currentThread().toString().logD()
                    AppDataBase.getDatabase(this@RoomActivity).getUserDao().insertUser(User("li",16,"is a bay"))
                    AppDataBase.getDatabase(this@RoomActivity).getUserDao().insertUser(User("wan",11,"is a dog"))
                    AppDataBase.getDatabase(this@RoomActivity).getUserDao().insertUser(User("hu",19,"is a cat"))
                }
            }

            tvQuern.setOnClickListener {
                runBlocking (Dispatchers.IO){
                    val queryUser = AppDataBase.getDatabase(this@RoomActivity).getUserDao().queryUser()
                    queryUser.toString().logD()

                }
            }
        }
    }

}