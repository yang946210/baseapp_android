package com.yang.appkt.menu.base

import android.os.Bundle
import com.yang.appkt.databinding.ActivityRoomBinding
import com.yang.appkt.viewmodel.RoomViewModel
import com.yang.ktbase.base.BaseActivity

class RoomActivity : BaseActivity<RoomViewModel, ActivityRoomBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
//        viewModel.roomData.observe(this){
//
//        }
//        binding.apply {
//            tvDelete.setOnClickListener {
//                lifecycleScope.launch(Dispatchers.IO) {
//                    AppDataBase.getDatabase(this@RoomActivity).getUserDao().deleteUserByName("li")
//                }
//
//            }
//            tvInsert.setOnClickListener {
//                lifecycleScope.launch(Dispatchers.IO) {
//                    Thread.currentThread().toString().logD()
//                    AppDataBase.getDatabase(this@RoomActivity).getUserDao()
//                        .insertUser(User("li", 16, "is a bay"))
//                }
//            }
//
//            tvQuern.setOnClickListener {
//                lifecycleScope.launch(Dispatchers.IO) {
//                    val queryUser =
//                        AppDataBase.getDatabase(this@RoomActivity).getUserDao().queryUser()
//                    queryUser.toString().logD()
//
//                }
//            }
//
//            tvQuernLivedata.setOnClickListener {
//                lifecycleScope.launch(Dispatchers.IO) {
//                    try {
//                        AppDataBase.getDatabase(this@RoomActivity).getUserDao().queryUserByName2().also {
//                            withContext(Dispatchers.Main){
//                                it.observe(this@RoomActivity){
//                                    it.toString().logD("======observer=======")
//                                }
//                            }
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//        }
    }

}