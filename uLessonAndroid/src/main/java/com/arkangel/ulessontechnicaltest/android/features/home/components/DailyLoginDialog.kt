package com.arkangel.ulessontechnicaltest.android.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arkangel.ulessontechnicaltest.android.R

@Composable
fun DailyLoginDialog(
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("reward.json"))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                composition = composition,
                iterations = 1,
            )

            Text(
                text = stringResource(R.string.daily_login_reward),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.W500
            )

            Text(
                text = stringResource(R.string.you_have_received_your_daily_reward_for_logging_in),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Okay")
            }
        }
    }
}
