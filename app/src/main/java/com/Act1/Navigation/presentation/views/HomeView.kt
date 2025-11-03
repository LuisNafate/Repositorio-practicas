package com.Act1.Navigation.presentation.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.Act1.Navigation.presentation.components.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController){
    Scaffold(

        topBar = {
            TopAppBar(
                title = {Text("Cuarto B")}
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton()
        }
    ) {
        Content(it, navController)
    }
}

@Composable
fun Content(innerPaddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomIconButton()

        NormalButton("Alix", onClick = {
            navController.navigate("Details")
        })
        NormalButton("Nafa", onClick =  {
            navController.navigate("Details2")
        })
        NormalButton("Rodo", onClick = {
            navController.navigate("Details3")
        })
        CustomOutlinedButton()

    }
}