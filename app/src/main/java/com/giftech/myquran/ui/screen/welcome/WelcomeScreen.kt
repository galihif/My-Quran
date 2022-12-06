package com.giftech.myquran.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R
import com.giftech.myquran.ui.components.GetStartedButton
import com.giftech.myquran.ui.theme.MyQuranTheme

@Composable
fun WelcomeScreen() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.title),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(Modifier.height(16.dp))
            Text(
                stringResource(R.string.desc),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_img_splash),
                    contentDescription = ""
                )
                GetStartedButton(
                    text = R.string.get_started,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = ButtonDefaults.MinHeight / 2),
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    MyQuranTheme {
        WelcomeScreen()
    }
}