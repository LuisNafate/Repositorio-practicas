package com.luisnafate.perifericobasicocamara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.luisnafate.perifericobasicocamara.ui.screens.CameraScreen
import com.luisnafate.perifericobasicocamara.ui.theme.PerifericoBasicoCamaraTheme

/**
 * Actividad principal de la aplicación.
 * Configura el tema de Compose y muestra la pantalla de la cámara.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            PerifericoBasicoCamaraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        CameraScreen()
                    }
                }
            }
        }
    }
}