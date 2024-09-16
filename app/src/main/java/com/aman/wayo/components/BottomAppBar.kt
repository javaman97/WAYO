package com.aman.wayo.components

import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.aman.wayo.R
import com.aman.wayo.Screens
import com.aman.wayo.ui.theme.PrimaryColor
import com.aman.wayo.ui.theme.PrimaryDarkColor


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { navController.navigate(Screens.HomeScreen.route)}) {
                Icon(painter = painterResource(id =R.drawable.house__1_), contentDescription = "Home Screen")
            }
            IconButton(onClick = { navController.navigate(Screens.OrdersScreen.route) }) {
                Icon(
                    painter = painterResource(id =R.drawable.my_orders),
                    contentDescription = "Orders Screen",
                )
            }
            IconButton(onClick = { navController.navigate(Screens.ProfileScreen.route) }) {
                Icon(
                    painter = painterResource(id =R.drawable.profile),
                    contentDescription = "Profile Screen",
                )
            }
        },
        containerColor = PrimaryColor ,
    contentColor = contentColorFor(PrimaryDarkColor),
    tonalElevation = BottomAppBarDefaults.ContainerElevation,)
}
