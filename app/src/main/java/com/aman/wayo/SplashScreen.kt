package com.aman.wayo

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    var startAnimation by remember { mutableStateOf(false) }

    // Animate offset to slide the icon to the left
    val offsetX: Dp by animateDpAsState(
        targetValue = if (startAnimation) (-100).dp else 0.dp,  // Slide 100.dp to the left
        animationSpec = tween(
            durationMillis = 1000,  // Animation duration
            easing = FastOutSlowInEasing  // Easing effect
        ), label = "leftSlideAnimation"
    )

    // Start animation after a delay
    LaunchedEffect(key1 = true) {
        delay(500)  // Delay before starting the animation
        startAnimation = true
        delay(2000)  // Delay to keep splash screen for a while
        navController.navigate(Screens.HomeScreen.route)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryColor), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.wayo_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .offset(x = offsetX, y = 0.dp)
                    .alpha(0.5f),
            )
        }
}

@Preview
@Composable
fun PreviewSplashScreen(){
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
