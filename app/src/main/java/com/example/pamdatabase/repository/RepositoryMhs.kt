package com.example.pamdatabase.repository
import com.example.pamdatabase.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface RepositoryMhs {

    suspend fun insertMhs(mahasiswa: Mahasiswa)

    fun getAllMhs(): Flow<List<Mahasiswa>>

    fun getMhs(nim: String): Flow<Mahasiswa>

    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    suspend fun updateMhs(mahasiswa:Mahasiswa)


}
