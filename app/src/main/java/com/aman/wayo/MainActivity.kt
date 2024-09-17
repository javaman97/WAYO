package com.aman.wayo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aman.wayo.components.BottomNavigationBar
import com.aman.wayo.components.TopAppBar
import com.aman.wayo.ui.theme.WayoTheme

private const val TAG = "MainActivity"
private  val isHome = Screens.HomeScreen.route
private  val myOrders = Screens.OrdersScreen.route
private  val myProfile = Screens.ProfileScreen.route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WayoTheme {
                val navController = rememberNavController()
                val showTopBar = remember {
                    mutableStateOf(true)
                }
                val bottomAppBar = remember {
                    mutableStateOf(true)
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if(showTopBar.value) {
                            TopAppBar(
                                title = when (currentRoute) {
                                    myOrders -> "My Orders"
                                    myProfile -> "Profile"
                                    else -> ""
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if (bottomAppBar.value)
                        BottomNavigationBar(navController = navController)
                    }
                ) { paddingValues ->
                    NavigationGraph(navController = navController, showTopBar,bottomAppBar, paddingValues)
                }
            }
        }
    }
  }

@Composable
fun NavigationGraph(
    navController: NavHostController,
    showTopBar: MutableState<Boolean>,
    bottomAppBar: MutableState<Boolean>,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            showTopBar.value = false
            bottomAppBar.value = false
            SplashScreen(navController = navController)
        }

        composable(Screens.SignupScreen.route) {
            showTopBar.value = false
            bottomAppBar.value = false
            SignUp(navController)
        }
        composable("Screens.OTPScreen.route/{phoneNo}") { backStackEntry ->
            val phoneNo = backStackEntry.arguments?.getString("phoneNo")
            OTPandDetaiilsScreen(navController,phoneNo= phoneNo ?: "" )
        }
        composable(isHome){
            showTopBar.value = false
            bottomAppBar.value = true
            HomeScreen(navController = navController, paddingValues)
        }
        composable(myOrders){
            showTopBar.value = true
            bottomAppBar.value = true
            MyOrdersScreen()
        }
        composable(myProfile){
            showTopBar.value = true
            bottomAppBar.value = true
            ProfileScreen()
        }
    }
}

