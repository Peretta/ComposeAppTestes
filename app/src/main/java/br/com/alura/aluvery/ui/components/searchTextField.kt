package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun searchTextField(
    searchText: String,
    onSearchChange: (String) -> Unit,
) {

    OutlinedTextField(
        value = searchText, onValueChange = { newValue ->
            onSearchChange(newValue)
        },
        Modifier
            .padding(
                top = 16.dp,
                end = 16.dp,
                start = 16.dp
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        label = {
            Text(text = "Produto")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "ícone de lupa")
        },
        placeholder = {
            Text("O que você procura?")
        }
    )
}