package com.giftech.myquran.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.ui.theme.Gray500
import com.giftech.myquran.ui.theme.fontsArab

@Composable
fun SurahItem(
    surah: Surah,
    onSurahClicked: (Int) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onSurahClicked(surah.nomor) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_bg_surah_number),
                contentDescription = ""
            )
            Text(surah.nomor.toString(), Modifier.align(Alignment.Center))
        }
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = surah.nama,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = "Mekah ${surah.jumlahAyat} ayat",
                color = Gray500
            )
        }
        Text(
            text = surah.asma,
            color = MaterialTheme.colors.primary,
            fontFamily = fontsArab,
            fontSize = 22.sp
        )
    }
}
