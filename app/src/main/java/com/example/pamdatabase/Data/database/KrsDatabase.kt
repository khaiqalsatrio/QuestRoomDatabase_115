package com.example.pamdatabase.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pamdatabase.Data.dao.MahasiswaDao // Mengimpor DAO Mahasiswa
import com.example.pamdatabase.Data.entity.Mahasiswa // Mengimpor entitas Mahasiswa

// Mendefinisikan database Room dengan entitas Mahasiswa
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    // Fungsi abstrak untuk mengakses DAO Mahasiswa
    abstract fun mahasiswaDao(): MahasiswaDao

    companion object {
        // Volatile untuk memastikan instance terbaru selalu terlihat oleh thread lain
        @Volatile
        private var instance: KrsDatabase? = null

        // Singleton pattern untuk memastikan hanya satu instance database yang digunakan
        fun getDatabase(context: Context): KrsDatabase {
            // Jika instance sudah ada, gunakan instance tersebut
            return instance ?: synchronized(this) {
                // Jika instance belum ada, buat menggunakan Room.databaseBuilder
                Room.databaseBuilder(
                    context.applicationContext, // Gunakan applicationContext untuk mencegah memory leak
                    KrsDatabase::class.java,    // Kelas database yang didefinisikan
                    "KrsDatabase"               // Nama database
                )
                    .build() // Membuat instance database
                    .also { instance = it } // Menyimpan instance ke variabel instance
            }
        }
    }
}
