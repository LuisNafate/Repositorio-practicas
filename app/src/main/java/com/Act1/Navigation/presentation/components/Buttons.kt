package com.Act1.Navigation.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/***
 * Project: CuartoA2
 * Package: com.danielflores.cuartoa2.components
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 14:22
 * All rights reserved 2025.
 **/

@Composable
fun NormalButton(
    title: String,
    onClick: (() -> Unit)? = null
){
    if (onClick == null){
        Button(
            onClick = {
                println("Como me quiero ir por favor")
            }
        ) {
            Text(title)
        }
        return
    }
    Button(
        onClick = onClick
    ) {
        Text(title)
    }
}

@Composable
fun CustomOutlinedButton(){
    OutlinedButton(
        onClick = {}
    ) {
        Text("Outlined")
    }
}

@Composable
fun CustomIconButton(){
    IconButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ""
        )
    }
}

@Composable
fun CustomFloatingActionButton(){
    FloatingActionButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
    }
}