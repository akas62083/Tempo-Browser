package com.example.tempo_browser.browScreen

data class BroUiState(
    val writing: String = "",
    val tabs: List<TabState> = emptyList(),
    val currentTab: TabState = TabState()
)
data class TabState(
    val url: String = "",
    val id: String = ""
)
