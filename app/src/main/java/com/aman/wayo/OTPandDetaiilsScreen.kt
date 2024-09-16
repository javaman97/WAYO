package com.aman.wayo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.components.OTPSheet
import com.aman.wayo.ui.theme.PrimaryDarkColor

@Composable
fun OTPandDetaiilsScreen(navController: NavController, phoneNo:String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryDarkColor), verticalArrangement = Arrangement.SpaceBetween){
        IconButton(onClick = {navController.navigate(Screens.SignupScreen.route)},
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            },
        )

        Text(text = "Get 50%* off on", fontSize = 28.sp, color = Color.White, modifier = Modifier.padding(start = 15.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "first 3 orders", fontSize = 28.sp, color = Color.White, modifier = Modifier.padding(start = 15.dp))

        Spacer(modifier = Modifier.height(14.dp))

        OTPSheet(phoneNo)
//        PersonalDetailsSheet()
    }
}



@Preview
@Composable
fun OTP(){
    val navController = rememberNavController()
    OTPandDetaiilsScreen(navController,"67")
}