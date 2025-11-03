

package com.Act1.Navigation.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.Act1.Navigation.data.List.students
/***
 * Project: CuartoA2
 * Package: com.danielflores.cuartoa2.presentation.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 14:26
 * All rights reserved 2025.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView2(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Details A")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        DetailsContent2(it)
    }
}

@Composable
fun DetailsContent2(innerPaddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        Text("${students[1].name} esta es tu informacion ")
        Text("${students[1].name}${students[2].description}")
    }
}