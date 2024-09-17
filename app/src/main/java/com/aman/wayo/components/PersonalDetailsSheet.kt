package com.aman.wayo.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.wayo.ui.theme.TertiaryColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun PersonalDetailsSheet() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
//        border = BorderStroke(0.5.dp, SecondaryVariantColor.copy(0.5f))
    ) {

        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()


        Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Enter your personal details",
                color = TertiaryColor,
                fontFamily = fontFamily,
                fontSize = 18.sp
            )

            TitlewithTextField("First Name*", KeyboardType.Text, firstName , onValueChange = {
                firstName = it
            })
            TitlewithTextField("Last Name", KeyboardType.Text, value = lastName) { newValue ->
                lastName = newValue
            }
            TitlewithTextField("Email ID", KeyboardType.Email, email, onValueChange = {
                email=it
            })

            Text(
                text = "Have a partner code?",
                color = TertiaryColor,
                fontFamily = fontFamily,
                fontSize = 18.sp,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )

            if (isFormValid)
            Button("Create Account",true)
            else
            {
                Toast.makeText(context,"Please enter the details",Toast.LENGTH_SHORT).show()
                Button("Create Account",false)
            }

        }
    }
}

@Composable
fun TitlewithTextField(label:String, keyboardType: KeyboardType,value: String, onValueChange: (String) -> Unit){
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = label,
            color = TertiaryColor,
            fontFamily = fontFamily,
            fontSize = 18.sp
        )
        var detail by remember { mutableStateOf("") }
        TextField(
            value = detail,
            onValueChange = {
                if (it.isNotEmpty()) {
                    detail = it
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),
            label = { Text("Phone Number?", fontFamily = fontFamily) },
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
            modifier = Modifier
                .padding(start = 8.dp),
            singleLine = true
        )
    }
}



