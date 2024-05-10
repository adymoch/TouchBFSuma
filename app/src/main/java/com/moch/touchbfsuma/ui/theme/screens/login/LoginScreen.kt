package com.moch.touchbfsuma.ui.theme.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moch.touchbfsuma.auth.AuthViewModel
import com.moch.touchbfsuma.navigation.ROUT_SIGNUP
import com.moch.touchbfsuma.ui.theme.themeBlue


@Composable
fun LoginScreen(navController: NavHostController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val authViewModel = AuthViewModel(navController,mContext)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(themeBlue)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {//Row for the app logo


        }//Row for the app logo


        //Text field
        TextField(
            value = email,
            onValueChange = {email = it},
            placeholder = { Text(text="Enter Your Email") },
            leadingIcon = { Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )
        Spacer(modifier = Modifier.height(10.dp))

        //End Text field
        //Text field
        TextField(
            value = password,
            onValueChange = {password = it},
            placeholder = { Text(text="Enter Your PasswordP") },
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "person")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
        Spacer(modifier = Modifier.height(10.dp))

        //End Text field
        //begin button
        OutlinedButton(onClick = {
            authViewModel.login(mContext, email, password)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(2.dp, Color.Blue)
        ) {
            Text(
                text="Login",
                color = Color.Blue,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        //End of outlined Button

        Text(
            text = "Don't Have an Account ? Signup",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .clickable {
                    navController.navigate(ROUT_SIGNUP)
                }
        )


    }

}
@Preview
@Composable
fun HomePreview(){
    LoginScreen(rememberNavController())
}