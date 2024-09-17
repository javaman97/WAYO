package com.aman.wayo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aman.wayo.R
import com.aman.wayo.Screens
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.aman.wayo.ui.theme.fontFamily

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", R.drawable.home, R.drawable.home_selected,Screens.HomeScreen.route),
        BottomNavItem("My Orders", R.drawable.my_orders,  R.drawable.my_orders_selected,Screens.OrdersScreen.route),
        BottomNavItem("Profile", R.drawable.profile, R.drawable.profile_selected,Screens.ProfileScreen.route)
    )

    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            val isSelected = currentRoute == item.route

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (isSelected) item.selectedIcon else item.normalIcon),
                        contentDescription = item.title,
                        tint = if (isSelected) PrimaryColor else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) PrimaryColor else TertiaryVariantColor,
                        fontFamily = fontFamily,
                        fontSize = 10.sp
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val normalIcon: Int,
    val selectedIcon: Int,
    val route: String
)
