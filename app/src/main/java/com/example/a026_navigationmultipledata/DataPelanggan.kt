package com.example.a026_navigationmultipledata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a026_navigationmultipledata.ui.theme._026_NavigationMultipleDataTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPelanggan(
    onNextButtonClicked: (MutableList<String>) -> Unit
){
    var nama by rememberSaveable { mutableStateOf("") }
    var noHp by rememberSaveable { mutableStateOf("") }
    var alamat by rememberSaveable { mutableStateOf("") }
    var ListData: MutableList<String> = mutableListOf(nama, noHp, alamat)

    Column (
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = nama, onValueChange = {nama = it}, label = { Text(text = "Nama")})
        OutlinedTextField(value = noHp, onValueChange = {noHp = it}, label = { Text(text = "Nomer Telepon")})
        OutlinedTextField(value = alamat, onValueChange = {alamat = it}, label = { Text(text = "Alamat")})

        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { onNextButtonClicked(ListData) }) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDataPelanggan() {
    _026_NavigationMultipleDataTheme {
        DataPelanggan(onNextButtonClicked = {})
    }
}
