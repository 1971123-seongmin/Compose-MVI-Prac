package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun SocialLoginRow() {
    val socialButtons = remember {
        listOf(
            Pair(R.drawable.btn_kakao, R.string.content_desc_kakao),
            Pair(R.drawable.btn_t_world, R.string.content_desc_t_world),
            Pair(R.drawable.btn_naver, R.string.content_desc_naver),
            Pair(R.drawable.btn_facebook, R.string.content_desc_facebook),
            Pair(R.drawable.btn_apple, R.string.content_desc_apple)
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        socialButtons.forEach { (iconRes, contentDescRes) ->
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = stringResource(id = contentDescRes),
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
            )
        }
    }
}