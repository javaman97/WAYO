package com.aman.wayo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.wayo.components.Button
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.PrimaryDarkColor
import com.aman.wayo.ui.theme.SecondaryColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun MyOrdersScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.padding(paddingValues)
            .background(Color.White),
    ) {

        var selectedIndex by remember { mutableIntStateOf(1) }
        val options = listOf("Ongoing", "Scheduled", "Past")
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 65.dp)
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = PrimaryColor.copy(0.2f),
                        activeContentColor = PrimaryDarkColor,
                        inactiveContainerColor = SecondaryColor,
                        inactiveContentColor = PrimaryDarkColor.copy(0.7f)
                    )
                ) {
                    // Only the text without any icon
                    Text(
                        text = label,
                        fontFamily = fontFamily,
                     //   color = if (index == selectedIndex) SecondaryColor else PrimaryDarkColor // Change text color based on selection
                    )
                }
            }
        }



        Column(modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.no_orders_img),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.7f).
                fillMaxHeight(0.3f),

            )
            Text("Ready to book your next order?", fontFamily = fontFamily, fontSize = 20.sp, color = PrimaryDarkColor)
        }
        Row(modifier = Modifier
            .align(Alignment.BottomCenter).padding(bottom = 65.dp)) {
            Button(text = "Let's Book", enableBtn = true)
        }

    }
}

@Preview
@Composable
fun PreviewOrderScreen(){
  MyOrdersScreen()
}