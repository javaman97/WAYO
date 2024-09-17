package com.aman.wayo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.wayo.components.ProfiFeatureCard
import com.aman.wayo.components.ProfileCard
import com.aman.wayo.ui.theme.PrimaryDarkColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun ProfileScreen() {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        , verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally){
        ProfileCard()
        Column {
            ProfiFeatureCard(R.drawable.sun, "Payment")
            ProfiFeatureCard(R.drawable.sun, "Help")
            ProfiFeatureCard(R.drawable.sun, "Serviceable Area")
            ProfiFeatureCard(R.drawable.sun, "Account Settings")
            ProfiFeatureCard(R.drawable.sun, "Policies")
        }
        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                color = PrimaryDarkColor,
                fontFamily = fontFamily,
                fontSize = 16.sp,
            )
            Text(
                text = "Version 1.1.4",
                color = PrimaryDarkColor,
                fontFamily = fontFamily,
                fontSize = 10.sp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen(){
    ProfileScreen()
}