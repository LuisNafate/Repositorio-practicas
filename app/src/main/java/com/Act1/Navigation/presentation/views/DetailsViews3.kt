

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
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.Act1.Navigation.data.List.students

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView3(navController: NavController){
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
        DetailsContent3(it)
    }
}

@Composable
fun DetailsContent3(innerPaddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        Text("${students[2].name} esta es tu informacion ",fontSize = 30.sp,
            fontWeight = Bold,)
        Text("${students[2].name}${students[2].description}")
    }
}