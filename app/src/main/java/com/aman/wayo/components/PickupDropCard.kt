package com.aman.wayo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.wayo.R
import com.aman.wayo.ui.theme.PinkColor
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.SecondaryColor
import com.aman.wayo.ui.theme.TertiaryColor
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun PickupDropCard(){
    Card(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp).padding(start = 8.dp, end = 8.dp),
        border = BorderStroke(1.5.dp, TertiaryColor)
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
             .background(SecondaryColor)
            .padding(15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column ( modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(painter = painterResource(id = R.drawable.location), contentDescription ="Pickup Icon" , tint = PrimaryColor)
                VerticalDivider(modifier = Modifier.fillMaxHeight(0.7f), thickness = 1.dp,)
                Icon(painter = painterResource(id = R.drawable.location), contentDescription ="Dropto Icon", tint = PinkColor)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = AbsoluteAlignment.Left){
                Text(text = "Pickup from?", fontSize= 18.sp, fontFamily = fontFamily, color = TertiaryVariantColor)
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
                Text(text = "Drop to?", fontSize= 18.sp, fontFamily = fontFamily, color = TertiaryVariantColor)
            }
        }
    }
}


@Preview
@Composable
fun PreviewPickupCard(){
    PickupDropCard()
}

