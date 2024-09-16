package com.aman.wayo.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.Screens
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.TertiaryColor
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.aman.wayo.ui.theme.fontFamily
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun OTPSheet(phoneNo:String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
//        border = BorderStroke(0.5.dp, SecondaryVariantColor.copy(0.5f))
    ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Enter OTP",
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontSize = 18.sp
                )
                Row( verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Send to +91",
                        color = TertiaryVariantColor,
                        fontFamily = fontFamily,
                        fontSize = 14.sp
                    )
                    Text(
                        phoneNo,
                        color = Color.Black,
                        fontFamily = fontFamily,
                        fontSize = 16.sp
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon( Icons.Default.Edit, contentDescription = "Edit Mobile Number",  modifier = Modifier.size(12.dp), tint = PrimaryColor )
                    }
                }

                var otpValue by remember {
                    mutableStateOf("")
                }


                    OtpTextField(otpText = otpValue,
                        onOtpTextChange = { value, otpInputFilled ->
                            otpValue = value
                        })


                Row(modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 12.dp, end = 12.dp)) {
                    Text(text = "Resend OTP in seconds", fontSize = 12.sp, color = TertiaryVariantColor, fontFamily = fontFamily)
                }
            }
    }
}




@Composable
fun OtpTextField(
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }
    var message by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
            val credential = PhoneAuthProvider.getCredential(verificationId ?: "", otpCode)
            signInWithPhoneAuthCredential(credential, auth) { resultMessage ->
                message = resultMessage
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .size(48.dp)
            .border(
                1.dp, when {
                    isFocused -> TertiaryColor
                    else -> TertiaryVariantColor
                }, RoundedCornerShape(12.dp)
            )
            .padding(2.dp),
        text = char,
        style = MaterialTheme.typography.headlineMedium,
        color = if (isFocused) {
            TertiaryColor
        } else {
            TertiaryVariantColor
        },
        textAlign = TextAlign.Center
    )
}





fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    onResult: (message: String) -> Unit,
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success
                val user = task.result?.user
                onResult("Authentication successful. User ID: ${user?.uid}")
              //  rememberNavController.navigate(Screens.HomeScreen.route)
            } else {
                // Sign in failed
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    onResult("Invalid OTP")
                } else {
                    onResult("Authentication failed: ${task.exception?.message}")
                }
            }
        }
}