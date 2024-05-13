package com.moch.touchbfsuma.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moch.touchbfsuma.R
import com.moch.touchbfsuma.auth.AuthViewModel
import com.moch.touchbfsuma.navigation.ROUT_HOME
import com.moch.touchbfsuma.navigation.ROUT_LOGIN
import com.moch.touchbfsuma.ui.theme.themeBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController){
    val mContext = LocalContext.current
     val authViewModel = AuthViewModel(navController,mContext)

    Column(modifier =
    Modifier
        .fillMaxSize()
        .background(themeBlue)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Code to launch the login screen after the splash
        val coroutine = rememberCoroutineScope()
        coroutine.launch {
            delay(2000)
if(authViewModel.isLoggedIn()) {
    navController.navigate(ROUT_HOME)
}else{
    navController.navigate(ROUT_LOGIN)

}
        }//end of it

        Image(painter = painterResource(id = R.drawable.img_1),
            contentDescription = "logo",
            modifier = Modifier.size(100.dp))




    }

}
@Preview
@Composable
fun SplashPreview(){
    SplashScreen(rememberNavController())
}