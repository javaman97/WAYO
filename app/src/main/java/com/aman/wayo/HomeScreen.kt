package com.aman.wayo

import ImageSliderWithDots
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.components.PickupDropCard
import com.aman.wayo.components.TopAppBarHome
import com.aman.wayo.ui.theme.SecondaryColor
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.aman.wayo.ui.theme.fontFamily


@Composable
fun HomeScreen(navController: NavController, paddingValues: PaddingValues){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .background(SecondaryColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        .padding(start = 8.dp, end = 8.dp)){

        TopAppBarHome()

        Spacer(modifier = Modifier.height(15.dp))

        PickupDropCard()

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(modifier = Modifier
            .fillMaxWidth(0.90f)
            .align(Alignment.CenterHorizontally), thickness = 1.dp)

        Spacer(modifier = Modifier.height(30.dp))

        val slider1Images = listOf(
                R.drawable.slider1_img1,
                R.drawable.slider1_img2,
                R.drawable.slider1_img3,
                R.drawable.slider1_img4)

        ImageSliderWithDots(true,120.dp, images = slider1Images)

        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.3f), thickness = 1.dp)
            Text(text = "Know your fleet", fontSize= 14.sp, fontFamily = fontFamily, color = TertiaryVariantColor)
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.7f),thickness = 1.dp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        val slider2Images = listOf( R.drawable.slider2_img1, R.drawable.slider2_img2)
        ImageSliderWithDots(false,200.dp,images = slider2Images)
    }
}

