package org.sopt.and.presentation.ui.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.component.AuthTextField
import org.sopt.and.presentation.component.SignUpToolbar
import org.sopt.and.presentation.component.SocialLoginRow
import org.sopt.and.presentation.component.SocialLoginTextDriver
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.DoubleDarkGray
import org.sopt.and.ui.theme.Gray100
import org.sopt.and.ui.theme.White

@Composable
fun SignUpScreen(
    signUpName: String,
    signUpPwd: String,
    signUpHobby: String,
    onNameChange: (String) -> Unit,
    onPwdChange: (String) -> Unit,
    onHobbyChange: (String) -> Unit,
    isPwdVisibility: Boolean,
    isPwdVisible: () -> Unit,
    onSignUpBtnClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Black)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpToolbar(
                content = stringResource(R.string.signup),
                trailingIcon = {
                    IconButton({}) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            )

            Spacer(Modifier.height(28.dp))
            Text(
                color = White,
                text = stringResource(R.string.signup_title),
                fontSize = 25.sp,
                lineHeight = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(24.dp))
            AuthTextField(
                value = signUpName,
                onValueChange = onNameChange,
                isPwdVisible = true,
                placeholder = R.string.signup_id_placeholder,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            Spacer(Modifier.height(8.dp))
            WarningTextGuide(R.string.signup_id_warning)

            // Password 입력 TextField
            Spacer(Modifier.height(8.dp))
            AuthTextField(
                value = signUpPwd,
                onValueChange = onPwdChange,
                placeholder = R.string.signup_pwd_placeholder,
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                isPwdVisible = isPwdVisibility,
                onPwdVisibilityChange = isPwdVisible
            )
            Spacer(Modifier.height(8.dp))
            WarningTextGuide(R.string.signup_pwd_warning)

            Spacer(Modifier.height(8.dp))
            AuthTextField(
                value = signUpHobby,
                onValueChange = onHobbyChange,
                isPwdVisible = true,
                placeholder = R.string.signup_hobby_placeholder,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            Spacer(Modifier.height(8.dp))
            WarningTextGuide(R.string.signup_hobby_warning)
            Spacer(Modifier.height(8.dp))

            // 소셜 로그인 Title
            Spacer(modifier = Modifier.height(32.dp))
            SocialLoginTextDriver(R.string.sign_social)

            // 소셜 로그인 버튼 Row
            Spacer(modifier = Modifier.height(16.dp))
            SocialLoginRow()

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                color = Gray100,
                text = stringResource(R.string.warning),
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 회원가입 버튼
        SignupButton(
            R.string.signup_btn_name,
            onClick = {
                onSignUpBtnClick()
            }
        )
    }
}

// 경고 문구 컴포넌트
@Composable
fun WarningTextGuide(@StringRes content: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = Gray100,
            modifier = Modifier.size(20.dp)
        )
        Text(
            color = Gray100,
            text = stringResource(content),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 4.dp)
                .offset(y = (-3).dp) // 위로 조정
        )
    }
}

// 회원가입 버튼
@Composable
fun SignupButton(
    @StringRes content: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(DoubleDarkGray)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(content),
            color = White,
            textAlign = TextAlign.Center
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSignUpScreen() {
//    SignUpScreen(
//        navigateUp = {},
//        navigateSignIn = {},
//        signUpEmail = stringResource(R.string.signup_id_placeholder),
//        signUpPwd = stringResource(R.string.signup_pwd_placeholder),
//        signUpHobby = stringResource(R.string.signup_hobby_placeholder),
//        onEmailChange = {},
//        onPwdChange = {},
//        isPwdVisibility = false,
//        isPwdVisible = {},
//        isSignUp = { _, _ -> },
//        signUpSuccess = false
//    )
//}