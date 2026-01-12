package com.example.tempo_browser.browScreen

import androidx.lifecycle.ViewModel
import com.example.tempo_browser.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

@HiltViewModel
class BroViewmodel @Inject constructor(
    private val repository: BookmarkRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(BroUiState())
    val uiState: StateFlow<BroUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                tabs = currentState.tabs + TabState(url = "https://google.com", id = "toppage"),
            )
        }
    }
    fun addTab(url: String) {
        _uiState.update { currentState ->
            currentState.copy(tabs = currentState.tabs + TabState(url = url, id = UUID.randomUUID().toString()))
        }
    }
    fun changeTab(tab: TabState) {
        _uiState.update { currentState ->
            currentState.copy(currentTab = tab)
        }
    }
}