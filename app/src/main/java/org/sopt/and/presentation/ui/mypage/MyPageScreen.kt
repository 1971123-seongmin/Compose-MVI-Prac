package org.sopt.and.presentation.ui.mypage

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.ui.signup.SignUpScreen
import org.sopt.and.core.designsystem.theme.Black
import org.sopt.and.core.designsystem.theme.Gray100
import org.sopt.and.core.designsystem.theme.Gray300
import org.sopt.and.core.designsystem.theme.GrayBlack
import org.sopt.and.core.designsystem.theme.White

@Composable
fun MyPageScreen(
    myHobby: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBlack)
            .navigationBarsPadding()
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Gray300)
        ) {
            // 컴포넌트 - 프로필 row
            MyProfileRow(myHobby)

            // 인증하기 >
            MyPageActionText(
                R.string.certification_title,
                R.string.certification_hide
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Black)
            )
            // 구매후기 >
            Spacer(modifier = Modifier.height(8.dp))
            MyPageActionText(
                R.string.no_subscription,
                R.string.purchase_hide
            )
        }

        // 전체 시청내역
        Spacer(modifier = Modifier.height(12.dp))
        HistoryRow(
            R.string.show_history,
            R.string.no_watch_history
        )

        // 관심 프로그램
        HistoryRow(
            R.string.interested_program,
            R.string.no_interested_program
        )
    }

}

// 프로필 Row
@Composable
fun MyProfileRow(
    // 이메일 , 알림 클릭, 설정 클릭
    email: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = stringResource(R.string.content_desc_my_profile_img),
            tint = White,
            modifier = Modifier.size(52.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = email,
            style = TextStyle(
                color = White,
                fontSize = 16.sp
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.content_desc_alarm_list),
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { } // 알림 클릭 이벤트
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.content_desc_settings),
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { } // 설정 클릭 이벤트
            )
        }
    }
}

@Composable
fun MyPageActionText(
    @StringRes content1: Int,
    @StringRes content2: Int,
) {
    Text(
        text = stringResource(content1),
        color = Gray100,
        fontSize = 14.sp,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
    Text(
        text = stringResource(content2),
        color = White,
        fontSize = 14.sp,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
    Spacer(Modifier.height(12.dp))
}

// 전체 시청 내역, 관심 프로그램
@Composable
fun HistoryRow(
    @StringRes title: Int,
    @StringRes content1: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(title),
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error_gray_24),
                    contentDescription = stringResource(R.string.content_desc_no_content),
                    tint = Gray100,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = stringResource(content1),
                    color = Gray100,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen(
        myHobby = stringResource(R.string.signup_id_placeholder),
    )
}