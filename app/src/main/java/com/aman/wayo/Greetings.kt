package com.aman.wayo

import java.time.LocalTime

fun getGreetingMessage(): String {
    val currentTime = LocalTime.now()
    return when (currentTime.hour) {
        in 0..11 -> "Good Morning,"
        in 12..17 -> "Good Afternoon,"
        else -> "Good Evening,"
    }
}


fun getGreetingIcons(): Int {
    val greetingMessage = getGreetingMessage()
    return if(greetingMessage == "Good Evening,")
        R.drawable.moon
    else
        R.drawable.sun

}
