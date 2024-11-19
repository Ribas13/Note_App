package com.example.ctwnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.example.ctwnoteapp.ui.screens.home.NoteAppHomePage

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
            AppTheme {
				NoteApp()
                //NoteAppHomePage()
            }
		}
	}
}


