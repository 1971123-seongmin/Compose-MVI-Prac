package org.sopt.and.presentation.ui.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.component.AuthTextField
import org.sopt.and.presentation.component.RoundedButton
import org.sopt.and.presentation.component.SignInToolbar
import org.sopt.and.presentation.component.SocialLoginRow
import org.sopt.and.presentation.component.SocialLoginTextDriver
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.Gray100
import org.sopt.and.ui.theme.Gray200
import org.sopt.and.ui.theme.White

@Composable
fun SignInScreen(
    navigateSignUp: () -> Unit,
    signInName: String,
    signInPwd: String,
    onNameChange: (String) -> Unit,
    onPwdChange: (String) -> Unit,
    isPwdVisibility: Boolean,
    isPwdVisible: () -> Unit,
    onSignInBtnClick:() -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignInToolbar(
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        )

        // 이메일 입력
        Spacer(modifier = Modifier.height(40.dp))
        AuthTextField(
            value = signInName,
            onValueChange = onNameChange,
            isPwdVisible = true,
            placeholder = R.string.id_placeholder,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // 비밀번호 입력
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            value = signInPwd,
            onValueChange = onPwdChange,
            isPwdVisible = isPwdVisibility,
            onPwdVisibilityChange = isPwdVisible,
            placeholder = R.string.pwd_placeholder,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // 로그인 버튼
        Spacer(Modifier.height(16.dp))
        RoundedButton(
            content = stringResource(R.string.signin),
            onClick = {
                onSignInBtnClick()
            }
        )

        // 아이디 찾기, 비밀번호 찾기, 회원가입 메뉴
        Spacer(Modifier.height(20.dp))
        AuthMenuRow( onSignUpClick = { navigateSignUp() })

        // 소셜 로그인 title text
        Spacer(modifier = Modifier.height(20.dp))
        SocialLoginTextDriver(R.string.sign_social)

        // 소션 로그인 버튼
        Spacer(modifier = Modifier.height(24.dp))
        SocialLoginRow()

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            color = Gray100,
            text = stringResource(R.string.warning),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun AuthMenuRow(
    onSignUpClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.find_id),
            style = TextStyle(color = Gray100),
            ///modifier = Modifier.clickable { onFindIdClick() }
        )
        Text(
            text = stringResource(R.string.signin_divider),
            style = TextStyle(color = Gray100),
            modifier = Modifier
                .offset(y = (3).dp) // 아래로 조정
        )
        Text(
            text = stringResource(R.string.reset_pwd),
            style = TextStyle(color = Gray100),
            //modifier = Modifier.clickable { onFindPwdClick() }
        )
        Text(
            text = stringResource(R.string.signin_divider),
            style = TextStyle(color = Gray100),
            modifier = Modifier
                .offset(y = (3).dp) // 아래로 조정
        )
        Text(
            text = stringResource(R.string.signup),
            style = TextStyle(color = Gray100),
            modifier = Modifier.clickable { onSignUpClick() }
        )
    }
}