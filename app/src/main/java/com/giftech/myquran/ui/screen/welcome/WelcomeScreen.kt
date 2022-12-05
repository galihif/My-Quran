package com.giftech.myquran.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.giftech.myquran.ui.theme.MyQuranTheme

@Composable
fun WelcomeScreen() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My Quran",
            fontWeight = FontWeight.Bold)
            Text("Learn Quran")
        }
    }
}

@Preview()
@Composable
fun WelcomePreview() {
    MyQuranTheme {
        WelcomeScreen()
    }
}