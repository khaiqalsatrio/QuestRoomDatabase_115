package com.example.pamdatabase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.pamdatabase.repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull

class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel () {
    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
        .filterNotNull()
        .map {
            HomeUiState(
                listMhs = it.toList(),
                isLoading = false,
            )
        }
}