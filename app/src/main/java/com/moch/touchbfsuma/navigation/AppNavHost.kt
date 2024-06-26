package com.moch.touchbfsuma.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moch.touchbfsuma.ui.theme.screens.home.HomeScreen
import androidx.navigation.compose.composable
import com.moch.touchbfsuma.ui.theme.screens.account.AccountScreen
import com.moch.touchbfsuma.ui.theme.screens.add.AddScreen
import com.moch.touchbfsuma.ui.theme.screens.login.LoginScreen
import com.moch.touchbfsuma.ui.theme.screens.signup.SignupScreen
import com.moch.touchbfsuma.ui.theme.screens.splash.SplashScreen
import com.moch.touchbfsuma.ui.theme.screens.splash.com.moch.touchbfsuma.ui.theme.screens.stocks.StocksScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController:NavHostController = rememberNavController(), startDestination:String = ROUT_SPLASH) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUT_SIGNUP) {
            SignupScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_STOCK) {
            StocksScreen(navController)
        }
        composable(ROUT_ACCOUNT) {
            AccountScreen(navController)
        }
        composable(ROUT_ADD) {
            AddScreen(navController)
        }



    }
}
