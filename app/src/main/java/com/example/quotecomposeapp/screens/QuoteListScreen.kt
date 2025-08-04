package com.example.quotecomposeapp.screens

import com.example.quotecomposeapp.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth      // <-- add this
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quotecomposeapp.models.Quote

@Composable
fun QuoteListScreen(
    data: Array<Quote>,
    onClick: (quote: Quote) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Quotes App",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()                 // <-- only fill width
                .padding(vertical = 24.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily(Font(R.font.montserrat))
        )

        // Give the list the rest of the screen
        QuoteList(
            data     = data,
            onClick  = onClick,
            modifier = Modifier.fillMaxSize()  // <-- pass a modifier so it can size itself
        )
    }
}
