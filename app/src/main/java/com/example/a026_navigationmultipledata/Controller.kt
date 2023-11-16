package com.example.a026_navigationmultipledata

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a026_navigationmultipledata.Data.SumberData.flavors

enum class Controller {
    Home,
    Rasa,
    Formulir,
    Detail,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsJumboAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier : Modifier = Modifier
){
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack){
                IconButton(onClick = navigasiUp){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsJumboApp(
    navController: NavHostController = rememberNavController(),
    viewModel: PociViewModel = viewModel()
) {
    Scaffold (
        topBar = {
            EsJumboAppBar(
                bisaNavigasiBack = false,
                navigasiUp = {/* TODO: implement back navigation*/}
            )
        }
    ) {innerPadding ->
        val uiState by viewModel.stateUi.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Controller.Home.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = Controller.Home.name){
                Home (
                    onNextButtonClicked = {
                        navController.navigate(Controller.Formulir.name) })
            }
            composable(route = Controller.Formulir.name){
                DataPelanggan(onNextButtonClicked =  { contactData ->
                    viewModel.setContact(contactData)
                    navController.navigate(Controller.Rasa.name)
                })
            }
            composable(route = Controller.Rasa.name){
                val context = LocalContext.current
                Order(
                    pilihanRasa = flavors.map{ id ->
                        context.resources.getString(id) },
                    onSelectionChanged = {viewModel.setRasa(it)},
                    onnConfirmButtonClicked = {viewModel.setJumlah(it)},
                    onNextButtonClicked = {navController.navigate(Controller.Detail.name)},
                    onCencelButtonClicked = {cencelOrderAndNavigateToHome(
                        viewModel,
                        navController
                    )
                    })
            }
            composable(route = Controller.Detail.name){
                Nota(PociUIState = uiState,
                    onCancelButtonClicked = {cencelOrderAndNavigateToRasa(navController)},
                )
            }
        }

    }
}

private fun cencelOrderAndNavigateToHome(
    viewModel: PociViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(Controller.Home.name, inclusive = false )
}

private fun cencelOrderAndNavigateToRasa(
    navController: NavHostController
){
    navController.popBackStack(Controller.Rasa.name, inclusive = false)
}