package com.example.pamdatabase.repository
import com.example.pamdatabase.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface RepositoryMhs {

    suspend fun insertMhs(mahasiswa: Mahasiswa)
    
}
