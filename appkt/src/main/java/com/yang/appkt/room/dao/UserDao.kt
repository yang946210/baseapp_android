package com.yang.appkt.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yang.appkt.room.entity.User
import java.util.concurrent.Flow

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Query("select * from user_info")
    fun queryUser():List<User>

    @Query("select * from user_info where name=:name")
    fun queryUserByName(name:String):List<User>

    @Query("select * from user_info")
    fun queryUserByName2():LiveData<List<User>>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from user_info where name=:name")
    fun deleteUserByName(name:String)

    /**
     * Transaction 和 suspend结合使用
     * @param user User
     */
    @Transaction
    suspend fun setLoggedInUser(user: User) {
        deleteUser(user)
        insertUser(user)
    }

    /**
     * 支持livedata
     * @param regions List<String>
     * @return LiveData<List<User>>
     */
    @Query("SELECT * FROM user_info WHERE name IN (:regions)")
    fun loadUsersFromRegionsSync(regions: List<String>): LiveData<List<User>>



}