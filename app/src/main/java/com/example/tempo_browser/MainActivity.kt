package com.example.tempo_browser

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tempo_browser.browScreen.BroScreen
import com.example.tempo_browser.browScreen.BroViewmodel
import com.example.tempo_browser.ui.theme.TempoBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TempoBrowserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val viewModel: BroViewmodel = hiltViewModel()
            BroScreen(
                viewmodel = viewModel
            )
        }
    }
   // var start by remember {mutableStateOf(false)}
   // Column(modifier = modifier.fillMaxSize()) {
   //     Button(
   //         onClick = {
   //             start = !start
   //         }
   //     ) {
   //         Text("Click!")
   //     }
   //     if(start) {
   //         val url = "https://www.google.com"
   //         AndroidView(factory = {
   //             android.webkit.WebView(it).apply {
   //                 settings.javaScriptEnabled = true
   //                 webViewClient = WebViewClient()
   //                 loadUrl(url)
   //             }
   //         })
   //     }
   // }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TempoBrowserTheme {
        Greeting("Android")
    }
}