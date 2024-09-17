package com.aman.wayo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.wayo.R
import com.aman.wayo.getGreetingIcons
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.PrimaryDarkColor
import com.aman.wayo.ui.theme.SecondaryColor
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun ProfileCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        border = BorderStroke(0.5.dp, PrimaryColor.copy(0.5f))
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryColor), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally ) {

            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(SecondaryColor), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ) {

                Image(
                    painter = painterResource(id = getGreetingIcons()),
                    contentDescription = "Profile Pic",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .border(1.dp, color = TertiaryVariantColor, shape = CircleShape)
                )

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start) {

                    Text(
                        text = "Aman Nishad",
                        color = PrimaryDarkColor,
                        fontFamily = fontFamily,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text="+918293848473",
                        color = PrimaryDarkColor,
                        fontFamily = fontFamily,
                        fontSize = 12.sp
                    )

                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button("Add GSTIN Details",true)

            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

@Composable
fun ProfiFeatureCard(icon:Int, fxnName:String,){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.5.dp, PrimaryColor.copy(0.4f))
    ) {
        Row(modifier = Modifier.fillMaxWidth().background(SecondaryColor).padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = "feature icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    text = fxnName,
                    color = PrimaryDarkColor,
                    fontFamily = fontFamily,
                    fontSize = 14.sp,
                )
            }
            Icon(painter = painterResource(id = R.drawable.forward_button), contentDescription = "feature icon",  modifier = Modifier.size(24.dp) .padding(end = 12.dp))
        }
    }
}


@Preview
@Composable
fun PreviewProfileCard(){
    ProfileCard()
    ProfiFeatureCard(R.drawable.sun,"Payment")
}