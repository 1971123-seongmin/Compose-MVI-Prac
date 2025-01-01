package org.sopt.and.presentation.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.BoxOverlayImage
import org.sopt.and.core.designsystem.theme.Black
import org.sopt.and.core.designsystem.theme.MoreDarkGray
import org.sopt.and.core.designsystem.theme.Teal200
import org.sopt.and.core.designsystem.theme.White
import org.sopt.and.presentation.data.HomeData
import org.sopt.and.presentation.type.MovieType

@Composable
fun HomeScreen(
    homeData: HomeData,
    onBannerItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // 상단 바
            item {
                HomeToolbar()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                MovieTypeCategories(homeData.categories)
            }

            // 배너 포스트 이미지 (Row)
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(homeData.bannerList) { banner ->
                        Image(
                            painter = painterResource(banner),
                            contentDescription = stringResource(R.string.content_desc_banner_image),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = 2.dp,
                                    color = MoreDarkGray,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { onBannerItemClick() }
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionWithMovieTypeList(stringResource(R.string.home_title_section_1), homeData.posterList1)
            }

            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionWithMovieTypeList(stringResource(R.string.home_title_section_2), homeData.posterList2)
            }

            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionWithMovieTypeList(stringResource(R.string.home_title_section_3), homeData.posterList3)
            }

            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_4))
            }

            item {
                Spacer(Modifier.height(4.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(homeData.top20List) { index, poster ->
                        BoxOverlayImage(
                            imageRes = poster,
                            overlayText = (index + 1).toString()
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionWithMovieTypeList(stringResource(R.string.home_title_section_5), homeData.posterList4)
            }
        }
        HomeBottomFixView(R.string.signup_offer)
    }
}

// 카테고리 Row
@Composable
fun MovieTypeCategories(categories: ImmutableList<MovieType>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            Text(
                text = category.name,
                color = MoreDarkGray,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        // 클릭 시 동작
                    }
            )
        }
    }
}

@Composable
fun HomeSectionWithMovieTypeList(
    title: String,
    @DrawableRes MovieTypeList: ImmutableList<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        HomeSectionHeader(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        MovieTypeListRow(MovieTypeList = MovieTypeList)
    }
}

// 홈화면 영화 LazyRow
@Composable
fun MovieTypeListRow(
    @DrawableRes MovieTypeList: ImmutableList<Int>,
    modifier: Modifier = Modifier
) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(MovieTypeList) { MovieType ->
            Image(
                painter = painterResource(MovieType),
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

// 홈 화면 제목 + > 버튼
@Composable
fun HomeSectionHeader(
    title: String,
    //onArrowClick: () -> Unit = {} // 화살표 아이콘 클릭 동작을 외부에서 지정 가능하게
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = "next",
            tint = White,
            modifier = Modifier.size(32.dp)
        )
    }
}

// 툴바
@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(   // Wavve
            painter = painterResource(id = R.drawable.ic_wavve_logo),
            contentDescription = "wave_logo",
            tint = White,
            modifier = Modifier
                .fillMaxWidth(0.3f) // 화면 width 30%
                .padding(start = 12.dp)
        )
        Row(   // 우측 아이콘 두 개
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cast_24dp), // wifi
                contentDescription = stringResource(R.string.content_desc_cast),
                tint = White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.live_tv_24dp), // tv
                contentDescription = stringResource(R.string.content_desc_tv_live),
                tint = White,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}

// 하단 고정 뷰
@Composable
fun HomeBottomFixView(
    @StringRes content: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Teal200),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.MailOutline,
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = stringResource(content),
            color = White,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        homeData = HomeData(),
        onBannerItemClick = {}
    )
}