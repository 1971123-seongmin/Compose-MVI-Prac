package org.sopt.and.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.Gray100
import org.sopt.and.core.designsystem.theme.Gray200

// 소셜 로그인 제목 driver
@Composable
fun SocialLoginTextDriver(@StringRes content: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            thickness = 1.dp,
            color = Gray200
        )

        Text(
            text = stringResource(content),
            fontSize = 16.sp,
            color = Gray100,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),  // 남은 공간을 모두 차지
            thickness = 1.dp,
            color = Gray200
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSocialLoginTextDriver() {
    SocialLoginTextDriver(content = R.string.sign_social) // 예시 문자열 리소스를 전달
}