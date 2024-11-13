package org.sopt.and.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.White

@Composable
fun BoxOverlayImage(
    imageRes: Int,
    overlayText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(180.dp)  // 비율로 수정
            .height(220.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        // 이미지
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Overlay Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // overlayText
        Text(
            text = overlayText,
            color = White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp, top = 16.dp)
        )
    }
}
