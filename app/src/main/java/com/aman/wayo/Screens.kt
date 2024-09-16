package com.aman.wayo

sealed class Screens (val route:String){
     data object SplashScreen : Screens("splash_screen")
     data object SignupScreen:Screens("signup_screen")
     data object OTPScreen:Screens("otpscreen")
     data object HomeScreen : Screens("home_screen")
     data object OrdersScreen : Screens("orders_screen")
     data object ProfileScreen : Screens("profile_screen")
}