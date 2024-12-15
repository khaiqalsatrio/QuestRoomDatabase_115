package com.example.pamdatabase.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pamdatabase.Data.entity.Mahasiswa // Mengimpor entitas Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {

    @Insert
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa
    )
}
