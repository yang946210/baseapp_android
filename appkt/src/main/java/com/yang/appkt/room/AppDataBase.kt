package com.yang.appkt.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yang.appkt.room.dao.UserDao
import com.yang.appkt.room.entity.User


/**
 * 数据量操作类
 */
@Database(version = 1, entities = [User::class])
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao():UserDao


    companion object{
        private const val DB_NAME = "app_name.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}