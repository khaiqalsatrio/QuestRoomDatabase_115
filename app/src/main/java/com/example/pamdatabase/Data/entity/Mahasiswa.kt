package com.example.pamdatabase.Data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Menandai kelas ini sebagai entitas database untuk tabel "mahasiswa"
@Entity(tableName = "mahasiswa")
data class Mahasiswa(
    @PrimaryKey // Menandai kolom NIM sebagai primary key
    val nim: String, // Nomor Induk Mahasiswa, unik untuk setiap mahasiswa
    val nama: String, // Nama mahasiswa
    val alamat: String, // Alamat mahasiswa
    val jenisKelamin: String, // Jenis kelamin mahasiswa
    val kelas: String, // Kelas mahasiswa
    val angkatan: String // Tahun angkatan mahasiswa
)
