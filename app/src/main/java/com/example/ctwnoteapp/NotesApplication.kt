package com.example.ctwnoteapp

import android.app.Application
import com.example.ctwnoteapp.data.AppContainer
import com.example.ctwnoteapp.data.AppDataContainer

class NotesApplication: Application() {
	lateinit var container: AppContainer

	override fun onCreate() {
		super.onCreate()
		container = AppDataContainer(this)
	}
}