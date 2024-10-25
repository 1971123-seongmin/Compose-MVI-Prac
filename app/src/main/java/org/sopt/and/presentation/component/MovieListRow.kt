package org.sopt.and.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// 홈화면 영화 LazyRow
@Composable
fun MovieListRow(
    movieList: List<Int>, // 영화 이미지의 리소스 ID 리스트
    modifier: Modifier = Modifier
) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movieList.size) { index ->
            Image(
                painter = painterResource(movieList[index]),
                contentDescription = "배너",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(140.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}