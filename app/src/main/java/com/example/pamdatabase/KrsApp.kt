package com.example.pamdatabase

import android.app.Application
import com.example.pamdatabase.dependeciesinjection.ContainerApp

// Kelas utama aplikasi, turunan dari Application
class KrsApp : Application() {

    // Menyimpan instance dari ContainerApp sebagai properti aplikasi
    lateinit var containerApp: ContainerApp

    // Dipanggil ketika aplikasi pertama kali dijalankan
    override fun onCreate() {
        super.onCreate()
        // Membuat instance dari ContainerApp dan menyimpannya di properti containerApp
        containerApp = ContainerApp(this)
        // Instance adalah objek yang dibuat dari class ContainerApp untuk mengelola dependensi aplikasi
    }
}
