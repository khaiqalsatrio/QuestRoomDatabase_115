package com.example.pamdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamdatabase.Data.entity.Mahasiswa
import com.example.pamdatabase.repository.RepositoryMhs
import com.example.pamdatabase.ui.navigation.DestinasiDetail
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    saveStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    // Perbaikan: pastikan MhsUiState memiliki konstruktor default yang valid
    var updateUiState by mutableStateOf(MhsUiState)
        private set

    // Perbaikan: pastikan objek DestinasiEdit atau DestinasiDetail digunakan dengan benar
    private val _nim: String = checkNotNull(saveStateHandle[DestinasiDetail.NIM]) // Ganti DestinasiEdit menjadi DestinasiDetail

    init {
        viewModelScope.launch {
            updateUiState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUiStateMhs()
        }
    }

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        updateUiState = updateUiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    fun validateFiled(): Boolean {
        val event = updateUiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong", // Perbaiki 'isNotEmpty()' pada 'nama'
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong", // Perbaiki 'Kelas' menjadi 'kelas'
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUiState.mahasiswaEvent

        if (validateFiled()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.updateMhs(currentEvent.toMahasiswaEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUiState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUiState = updateUiState.copy(snackBarMessage = null)
    }
}

fun Mahasiswa.toUiStateMhs(): MhsUiState = MhsUiState(
    mahasiswaEvent = this.toDetailUiEvent(),
)
