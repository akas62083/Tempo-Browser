package com.example.tempo_browser.browScreen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BroScreen(
    viewmodel: BroViewmodel,
) {
    val uiState by viewmodel.uiState.collectAsState()
    var title by remember {mutableStateOf("")}
    val context = LocalContext.current
    val webView = remember(uiState.currentTab.id) {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
    }
    var isOpened by remember {mutableStateOf(false)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(30.dp)) {
                            TextField(
                                value = title,
                                onValueChange = {title = it}
                            )
                            Button(
                                onClick = {
                                    viewmodel.addTab("https://www.google.com/search?q=$title")
                                    title = ""
                                }
                            ) {
                                Text("検索")
                            }
                            Box(modifier = Modifier.clickable {isOpened = !isOpened}) {
                                Text("＠")
                                DropdownMenu(
                                    expanded = isOpened,
                                    onDismissRequest = {isOpened = false}
                                ) {
                                    uiState.tabs.forEach { tab ->
                                        DropdownMenuItem (
                                            content = {
                                                Column {
                                                    Text(tab.url)
                                                    Text(tab.id)
                                                }
                                            },
                                            onClick = {viewmodel.changeTab(tab)}
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {}
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.Red)) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { webView },
                update = { view ->
                    if(view.url != uiState.currentTab.url && uiState.currentTab.url.isNotEmpty()) {
                        view.loadUrl(uiState.currentTab.url)
                    }
                }
            )
        }
    }
}