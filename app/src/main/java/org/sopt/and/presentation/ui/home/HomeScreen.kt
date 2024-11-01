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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.enum.Movie
import org.sopt.and.presentation.component.BoxOverlayImage
import org.sopt.and.presentation.component.HomeSectionHeader
import org.sopt.and.presentation.component.MovieListRow
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.MoreDarkGray
import org.sopt.and.ui.theme.Teal200
import org.sopt.and.ui.theme.White

@Composable
fun HomeScreen(

) {
    // 영화 리스트 -> 나중에 뷰모델로 이동
    val categories = persistentListOf(
        Movie.뉴클래식,
        Movie.드라마,
        Movie.영화,
        Movie.예능,
        Movie.애니,
        Movie.해외시리즈
    )
    val bannerList = persistentListOf(
        R.drawable.ic_banner_poster_tracer,
        R.drawable.ic_banner_poster_game_of_blood,
        R.drawable.ic_banner_poster_s_f_eight
    )
    val postList1 = persistentListOf(
        R.drawable.poster_madmax,
        R.drawable.poster_zootopia,
        R.drawable.poster_ready
    )
    val postList2 = persistentListOf(
        R.drawable.poster_parasite,
        R.drawable.poster_suicide_squad,
        R.drawable.poster_attack
    )
    val postList3 = persistentListOf(
        R.drawable.poster_chocolate,
        R.drawable.poster_inception,
        R.drawable.poster_sourcecode,
        R.drawable.poster_zootopia
    )

    val postList4 = persistentListOf(
        R.drawable.poster_spiderman3,
        R.drawable.poster_zootopia,
        R.drawable.poster_ready,
        R.drawable.ic_banner_poster_tracer
    )

    val top20List = persistentListOf(
        R.drawable.poster_inception,
        R.drawable.poster_spiderman2,
        R.drawable.poster_spiderman3,
        R.drawable.poster_attack,
        R.drawable.poster_stepup4
    )

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
            // 첫 번째 아이템 -> 상단 바
            item {
                HomeToolbar()
            }


            // 두 번째 아이템 -> 카테고리 (뉴클래식, 드라마, 영화, 예능, 애니, 해외시리즈)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategories(categories)
            }

            // 배너 포스트 이미지 (Row)
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(bannerList.size) { index ->
                        Image(
                            painter = painterResource(bannerList[index]),
                            contentDescription = "배너",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = 2.dp,
                                    color = MoreDarkGray,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )
                    }
                }
            }

            // 홈 섹션 1 - 믿고 보는 웨이브 에디터 추천작
            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_1))
            }
            // 믿고 보는 웨이브 에디터 추천작 (영화 리스트 - Row)
            item {
                Spacer(Modifier.height(4.dp))
                MovieListRow(postList1)
            }

            // 홈 섹션 2 - 실시간 인기 콘텐츠
            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_2))
            }
            // 실시간 인기 콘텐츠 (영화 리스트 - Row)
            item {
                Spacer(Modifier.height(4.dp))
                MovieListRow(postList2)
            }

            // 홈 섹션 3 - 오직 웨이브에서
            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_3))
            }
            // 실시간 인기 콘텐츠 (영화 리스트 - Row)
            item {
                Spacer(Modifier.height(4.dp))
                MovieListRow(postList3)
            }

            // 홈 섹션 4 - 오늘의 TOP 20
            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_4))
            }
            // 홈 섹션 4 - 오늘의 TOP 20
            item {
                Spacer(Modifier.height(4.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(top20List.size) { index ->
                        BoxOverlayImage(
                            imageRes = top20List[index],
                            overlayText = (index + 1).toString()
                        )
                    }
                }
            }

            // 홈 섹션 5 - 당한 대로 갚아 줄게
            item {
                Spacer(Modifier.height(24.dp))
                HomeSectionHeader(stringResource(R.string.home_title_section_5))
            }
            // 당한 대로 갚아 줄게 (영화 리스트 - Row)
            item {
                Spacer(Modifier.height(4.dp))
                MovieListRow(postList4)
            }


        }

        // 하단 뷰
        HomeBottomFixView(R.string.signup_offer)
    }
}

@Composable
fun MovieCategories(categories: ImmutableList<Movie>) {
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