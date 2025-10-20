package com.luisnafate.view_model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import com.luisnafate.view_model.Counter

class MainActivity : ComponentActivity() {
    private val counterViewModel: Counter by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterView(counterViewModel)
        }
    }
}

@Composable
fun CounterView(counterViewModel: Counter) {
    val count by counterViewModel.counter
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contador: $count",
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { counterViewModel.add() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00897B)
            )
        ) {
            Text("Sumar +1")
        }
    }
}