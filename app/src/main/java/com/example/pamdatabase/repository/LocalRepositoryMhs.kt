package com.example.pamdatabase.repository

import com.example.pamdatabase.Data.dao.MahasiswaDao
import com.example.pamdatabase.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow


class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
) : RepositoryMhs {


    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}
