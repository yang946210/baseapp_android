package com.yang.appkt.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yang.appkt.room.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user_info")
    fun queryUser():List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_info WHERE name=:yang")
    fun deleteUserByName(yang:String)

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