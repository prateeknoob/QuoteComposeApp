package com.example.quotecomposeapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter
import com.example.quotecomposeapp.models.Quote

@Composable
fun QuoteList(
    data: Array<Quote>,
    onClick: (Quote) -> Unit,
    modifier: Modifier = Modifier
) {
    // convert Array<Quote> to List<Quote> once
    val quotes = remember(data) { data.toList() }
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(
            items = quotes,
            key   = { it.text.hashCode() }  // or another unique key
        ) { quote ->
            // 👇 here’s the only change: call your styled item
            QuoteListItem(
                quote    = quote,
                onClick  = onClick,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    }

    // (optional) keep your infinite‐scroll hook if you need it
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .filter { it > quotes.size - 5 }
            .collect { /* load more */ }
    }
}
