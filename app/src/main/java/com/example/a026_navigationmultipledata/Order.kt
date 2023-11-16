package com.example.a026_navigationmultipledata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a026_navigationmultipledata.ui.theme._026_NavigationMultipleDataTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Order(
    pilihanRasa: List<String>,
    onSelectionChanged: (String) -> Unit,
    onnConfirmButtonClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCencelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier)
{
    var rasaYgDipilih by rememberSaveable { mutableStateOf("") }
    var textJmlBeli by remember { mutableStateOf("") }
    Column(modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween){
        Column (modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            pilihanRasa.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = rasaYgDipilih == item,
                    onClick = {
                        rasaYgDipilih = item
                        onSelectionChanged(item)
                    }
                ),

                    verticalAlignment = Alignment.CenterVertically){

                    RadioButton(selected = rasaYgDipilih == item, onClick = {rasaYgDipilih = item
                        onSelectionChanged(item)}
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.padding_medium),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource( R.dimen.padding_medium)),
            )
            {
                OutlinedTextField(value = textJmlBeli, singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(150.dp),
                    label = { Text(text = "Jumlah Order")},
                    onValueChange = {
                        textJmlBeli = it
                    }
                )
                Button (
                    modifier = Modifier.weight(1f),
                    enabled = textJmlBeli.isNotEmpty(),
                    onClick = {onnConfirmButtonClicked(textJmlBeli.toInt())}
                ){
                    Text(stringResource(R.string.confirm))
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedButton(modifier = Modifier.weight(1f), onClick = onNextButtonClicked) {
                    Text(stringResource(R.string.Cencel))
                }
                Button(modifier = Modifier.weight(1f),
                    enabled = textJmlBeli.isNotEmpty(),
                    onClick = onCencelButtonClicked)
                {
                    Text(stringResource(R.string.next))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrder() {
    _026_NavigationMultipleDataTheme {
        Order(
            pilihanRasa = listOf("Rasa1", "Rasa2"), // Sesuaikan dengan pilihan rasa yang diperlukan
            onSelectionChanged = { /* handle selection change */ },
            onnConfirmButtonClicked = { /* handle confirm button click */ },
            onNextButtonClicked = { /* handle next button click */ },
            onCencelButtonClicked = { /* handle cancel button click */ }
        )
    }
}
