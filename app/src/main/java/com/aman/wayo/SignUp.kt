package com.aman.wayo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.fontFamily
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun SignUp(navController: NavController) {
    var phoneNo by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }
    var isCodeSent by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
            Image(
                painter = painterResource(id = R.drawable.signup_image),
                contentDescription = "App Logo",
                modifier = Modifier
                    .graphicsLayer(scaleX = 1.65f, scaleY = 1.7f)
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
            )

        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(start = 10.dp, bottom = 85.dp)) {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.Start){

                Image(
                    painter = painterResource(id = R.drawable.wayo_black),
                    contentDescription = "App Logo"
                )
                Text(
                    text = "Reliable . Safe . Fast Delivery",
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.1f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.ind_flag),
                                contentDescription = "Indian Flag",
                                modifier = Modifier
                                    .size(42.dp)
                                    .padding(start = 8.dp)
                            )

                            Text(
                                text = "+91",
                                fontFamily = fontFamily,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )

                            TextField(
                                value = phoneNo,
                                onValueChange = {
                                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                                        phoneNo = it
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                label = { Text("Phone Number?", fontFamily = fontFamily) },
                                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                                modifier = Modifier.fillMaxHeight(),
                                singleLine = true,
                                colors = TextFieldDefaults.colors(

                                )
                            )
                        }
                    }

                    if (phoneNo.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp)
                                .fillMaxHeight(0.1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (phoneNo.length == 10) PrimaryColor else Color.Gray),

                           // colors = (if (phoneNo.length == 10) CardDefaults.cardColors(Color.White) else CardDefaults.cardColors(Color.Gray)) // Change background based on phone number len
                        ) {
                                IconButton(
                                  onClick = {
                                      startPhoneNumberVerification(phoneNo, auth) { id ->
                                          verificationId = id
                                          isCodeSent = true
                                      }
                                      navController.navigate("Screens.OTPScreen.route/$phoneNo")
                                  },
                                ){
                                    Icon(painterResource(id = R.drawable.forward_button), contentDescription = "Edit Mobile Number",  modifier = Modifier
                                        .size(12.dp)
                                        .align(Alignment.Center), tint = PrimaryColor )
                                }
                            }
                    }
                }
                Text(
                    text = "By continuing, you are accepting that you have read and accepted our ",
                    fontSize = 12.sp,
                    fontFamily = fontFamily,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .align(Alignment.Start)
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Terms of Use",
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                        color = PrimaryColor,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    Text(
                        text = "and",
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                        color = Color.White,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                        color = PrimaryColor,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
    }
}

fun startPhoneNumberVerification(
    phoneNumber: String,
    auth: FirebaseAuth,
    onVerificationCompleted: (verificationId: String) -> Unit
) {
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("PhoneAuth", "Verification completed: $credential")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.e("PhoneAuth", "Verification failed: ${e.message}")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("PhoneAuth", "Code sent: $verificationId")
            onVerificationCompleted(verificationId)
        }
    }

    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(phoneNumber) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
      //  .setActivity() // Your activity
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}


@Preview
@Composable
fun PreviewSignup(){
    SignUp(rememberNavController())
}

