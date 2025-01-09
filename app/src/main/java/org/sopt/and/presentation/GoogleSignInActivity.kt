package org.sopt.and.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.google.GoogleSignInRepository
import org.sopt.and.presentation.viewmodel.GoogleSignInViewModel
import javax.inject.Inject

@AndroidEntryPoint
class GoogleSignInActivity: AppCompatActivity() {

    @Inject
    lateinit var googleSignInRepository: GoogleSignInRepository

    private val viewModel: GoogleSignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel에서 로그인 처리 및 결과 처리 -> 구글 서버에서 ID Token이 도착한 뒤에 finish()
        lifecycleScope.launch {
            viewModel.googleLogin(googleSignInRepository)
                .onSuccess {
                    // 로그인 성공 시 결과 설정 후 종료
                    val resultIntent = Intent().apply {
                        putExtra("success", "success")
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
                .onFailure { exception ->
                    // 로그인 실패 시 결과 설정 후 종료
                    val resultIntent = Intent().apply {
                        putExtra("error", exception.message) // 에러 메시지 전달
                    }
                    setResult(Activity.RESULT_CANCELED, resultIntent)
                    finish()
                }
        }
    }
}