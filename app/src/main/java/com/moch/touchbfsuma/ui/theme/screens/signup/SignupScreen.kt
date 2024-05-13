package com.moch.touchbfsuma.ui.theme.screens.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.moch.touchbfsuma.R
import com.moch.touchbfsuma.auth.AuthViewModel
import com.moch.touchbfsuma.data.User
import com.moch.touchbfsuma.navigation.ROUT_LOGIN
import com.moch.touchbfsuma.navigation.ROUT_SIGNUP
import com.moch.touchbfsuma.ui.theme.themeBlue

@Composable
fun SignupScreen(navController: NavHostController){
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(themeBlue)
        .verticalScroll(rememberScrollState())
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val authViewModel = AuthViewModel(navController,mContext)
    val auth = FirebaseAuth.getInstance()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {//Row for the app logo
        Image(painter = painterResource(id = R.drawable.img_1),
            contentDescription = "logo",
            modifier = Modifier.size(100.dp))

    }//Row for the app logo

    //Text field
    TextField(
        value = username,
        onValueChange = {username = it},
        placeholder = { Text(text="Enter Your Username") },
        leadingIcon = { Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "person")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

    )
    Spacer(modifier = Modifier.height(10.dp))

    //End Text field
    //Text field
    TextField(
        value = email,
        onValueChange = {email = it},
        placeholder = { Text(text="Enter Your Email Address") },
        leadingIcon = { Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "person")
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
        placeholder = { Text(text="Enter Your Password") },
        leadingIcon = { Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "person")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

    )
    Spacer(modifier = Modifier.height(10.dp))

    //End Text field
    //begin button
    Button(onClick = {
        val user = User(
            username = username,
            userEmail = email,
            userId = auth.currentUser?.uid.toString(),
            imageUrl = ""
        )
        authViewModel.saveUserData(user,mContext)
        authViewModel.signUp(mContext, email, password)
    },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.Blue)
    ) {
        Text(
            text="Register",
            color = Color.Blue,
            fontSize = 20.sp)
    }
    Spacer(modifier = Modifier.height(10.dp))
    //End of outlined Button

    Text(
        text = "Already  Have an Account ? Login",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Serif,
        modifier = Modifier
            .clickable {
                navController.navigate(ROUT_LOGIN)
            }
    )

}

}
@Preview
@Composable
fun SignupPreview(){
    SignupScreen(rememberNavController())
}