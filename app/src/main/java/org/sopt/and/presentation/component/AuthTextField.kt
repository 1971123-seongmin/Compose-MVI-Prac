package org.sopt.and.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray100
import org.sopt.and.ui.theme.Gray200
import org.sopt.and.ui.theme.White

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholder: Int,
    modifier: Modifier = Modifier,
    isPwdVisible: Boolean = false,
    onPwdVisibilityChange: (() -> Unit)? = null
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(
                color = Gray200,
                shape = RoundedCornerShape(4.dp)
            ),
        textStyle = TextStyle(
            color = White,
            fontSize = 16.sp
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if(value.isEmpty()) {
                        Text(
                            text = stringResource(placeholder),
                            style = TextStyle(
                                color = Gray100,
                                fontSize = 14.sp
                            ),
                        )
                    }
                    innerTextField()
                }

                // 비밀번호 show/hide
                if (onPwdVisibilityChange != null) {
                    Text(
                        text = if (isPwdVisible) stringResource(R.string.show) else stringResource(R.string.hide),
                        style = TextStyle(
                            color = White,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .clickable { onPwdVisibilityChange() }  // 클릭 가능하게 설정
                            .padding(start = 8.dp)  // 여백 추가
                    )
                }
            }
        },
        visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation()
    )

}