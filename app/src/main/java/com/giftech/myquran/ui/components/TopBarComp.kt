@file:OptIn(ExperimentalAnimationApi::class)

package com.giftech.myquran.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R

@Composable
fun TitleBar(
    title: String,
    isVisible: Boolean = false,
    isLoaded:Boolean = false,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextTitle(text = title, fontSize = 16.sp)
                AnimatedVisibility(
                    visible = isVisible,
                    enter = scaleIn(spring()),
                    exit = scaleOut(spring()),
                ) {
                    if (isLoaded){
                        Icon(
                            imageVector = Icons.Rounded.VolumeUp,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.Downloading,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    keyword: String = "",
    onBack: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        keyboardController?.show()
        focusRequester.requestFocus()
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = ""
            )
        }
        TextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
                .focusRequester(focusRequester),
            value = keyword,
            onValueChange = onValueChange,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background
            ),
            placeholder = {
                Text(stringResource(R.string.search_surah))
            }
        )
    }
}

