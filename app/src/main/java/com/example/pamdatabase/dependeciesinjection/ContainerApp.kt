package com.example.pamdatabase.dependeciesinjection

import android.content.Context
import com.example.pamdatabase.Data.database.KrsDatabase
import com.example.pamdatabase.repository.LocalRepositoryMhs
import com.example.pamdatabase.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}