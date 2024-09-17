package com.aman.wayo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aman.wayo.getGreetingIcons
import com.aman.wayo.getGreetingMessage
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.PrimaryDarkColor
import com.aman.wayo.ui.theme.SecondaryColor
import com.aman.wayo.ui.theme.fontFamily


@Composable
fun TopAppBar(
    title: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        border = BorderStroke(0.5.dp, PrimaryColor.copy(0.6f))
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(SecondaryColor))
        {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = title,
                    color = PrimaryColor,
                    fontFamily = fontFamily,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }


@Composable
fun TopAppBarHome() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        border = BorderStroke(0.5.dp, PrimaryColor.copy(0.6f))
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryColor), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start ) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = getGreetingIcons()),
                        contentDescription = "Greetings Icon",
                        modifier = Modifier.size(28.dp),
                        tint = PrimaryColor
                    )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    getGreetingMessage(),
                    color = PrimaryColor,
                    fontFamily = fontFamily,
                    fontSize = 14.sp
                )

                Text(
                    "Hassle free pickups and drops",
                    color = PrimaryColor,
                    fontFamily = fontFamily,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }
}

@Preview
@Composable
fun PreviewTopBarHome(){
    TopAppBarHome()
    TopAppBar(title = "My Profile")
}















