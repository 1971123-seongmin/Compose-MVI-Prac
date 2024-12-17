package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.WavveBlue
import org.sopt.and.core.designsystem.theme.White

@Composable
fun RoundedButton(
    content: String,
    onClick: () -> Unit,
) {
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = WavveBlue),
        shape = RoundedCornerShape(32.dp)

    ) {
        Text(
            text = content,
            style = TextStyle(fontSize = 16.sp),
            color = White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoundedButton() {
    RoundedButton(
        content = stringResource(R.string.signin),
        onClick = {}
    )
}
