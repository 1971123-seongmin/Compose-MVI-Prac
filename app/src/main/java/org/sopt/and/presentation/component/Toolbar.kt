package org.sopt.and.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.White

@Composable
fun SignUpToolbar(
    modifier: Modifier = Modifier,
    content: String,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .defaultMinSize(minHeight = 36.dp)
            .height(36.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            leadingIcon()
        }

        Text(
            text = content,
            color = White,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            trailingIcon()
        }
    }

}

@Composable
fun SignInToolbar(
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(36.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            leadingIcon()
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_wavve_logo),
            contentDescription = "wave_logo",
            tint = White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            trailingIcon()
        }
    }

}