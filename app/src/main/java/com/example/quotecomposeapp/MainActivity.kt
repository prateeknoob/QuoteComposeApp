package com.example.quotecomposeapp

import QuoteDetail
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quotecomposeapp.screens.QuoteList
import com.example.quotecomposeapp.screens.QuoteListScreen
import com.example.quotecomposeapp.ui.theme.QuoteComposeAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssetfromFile(applicationContext) //data on IO thread
        }
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}


//app crashed when on main thread so coroutine scope to run it on IO thread
//performance improvement

@Composable
fun App() {             //data on main thread
    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentpage.value == Pages.LISTING){
            QuoteListScreen(data=DataManager.data) {
                DataManager.switchPages(it)
            }
        }
        else{
            DataManager.currentQuote?.let { QuoteDetail(quote=it) }
        }

    }
    else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)
        ){
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleMedium
                )
        }
    }
}



enum class Pages{
    LISTING,
    DETAIL
}