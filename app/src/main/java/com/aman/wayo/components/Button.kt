package com.aman.wayo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.SecondaryVariantColor
import com.aman.wayo.ui.theme.TertiaryColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun Button(text:String, enableBtn:Boolean){
    Button(onClick = {

    }, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp), enabled = enableBtn, shape =
    RoundedCornerShape(12.dp),
        colors = ButtonColors(containerColor = PrimaryColor, contentColor = Color.White, disabledContainerColor = SecondaryVariantColor, disabledContentColor = TertiaryColor),

    ){
        Text(text = text, fontFamily= fontFamily, color = Color.White)
    }
}