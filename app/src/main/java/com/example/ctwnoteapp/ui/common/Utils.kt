package com.example.ctwnoteapp.ui.common

import androidx.compose.ui.graphics.Color
import java.util.Random

fun generateColor(): Color {
	val random = Random()

	val red = random.nextInt(256)
	val green = random.nextInt(256)
	val blue = random.nextInt(256)
	return Color(red, green, blue)
}

private fun textColor(color: Color): Color {
	val red = color.red
	val green = color.green
	val blue = color.blue

	val brightness = 0.2126 * red + 0.7152 * green + 0.0722 * blue

	return if (brightness < 0.5)
		Color.White
	else
		Color.Black
}