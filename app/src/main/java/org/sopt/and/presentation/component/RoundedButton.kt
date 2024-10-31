package org.sopt.and.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.WavveBlue
import org.sopt.and.ui.theme.White

@Composable
fun RoundedButton(
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = WavveBlue),
        shape = RoundedCornerShape(30.dp)

    ) {
        Text(
            text = content,
            style = TextStyle(fontSize = 16.sp),
            color = White,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}