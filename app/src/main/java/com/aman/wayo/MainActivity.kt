package com.aman.wayo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.components.BottomNavigationBar
import com.aman.wayo.components.TopAppBar
import com.aman.wayo.ui.theme.WayoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WayoTheme {
               val navController = rememberNavController()
                val showTopBar = remember {
                    mutableStateOf(false)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (showTopBar.value) {
                            TopAppBar("My Orders", navController) {
                            }
                        }
                    },
                    bottomBar = {
                        if (showTopBar.value)
                        BottomNavigationBar(navController = navController)
                    }
                ) {
                    Column(Modifier.padding(it)) {
                        Router(navController = navController, showTopBar)
                    }
                }
            }
        }
    }
  }

@Composable
fun Router(navController: NavHostController, showTopBar: MutableState<Boolean>,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            showTopBar.value = false
            SplashScreen(navController = navController)
        }

        composable(Screens.SignupScreen.route) {
            showTopBar.value = false
            SignUp(navController)
        }
        composable("Screens.OTPScreen.route/{phoneNo}") { backStackEntry ->
            val phoneNo = backStackEntry.arguments?.getString("phoneNo")
            OTPandDetaiilsScreen(navController,phoneNo= phoneNo ?: "" )
        }
        composable(Screens.HomeScreen.route){
            showTopBar.value = false
            HomeScreen(navController = navController)
        }
        composable(Screens.OrdersScreen.route){
            showTopBar.value = true
        }
        composable(Screens.ProfileScreen.route){
            showTopBar.value = true
        }
    }
}

