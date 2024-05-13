package com.moch.touchbfsuma.ui.theme.screens.splash.com.moch.touchbfsuma.ui.theme.screens.stocks


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moch.touchbfsuma.R
import com.moch.touchbfsuma.data.Product
import com.moch.touchbfsuma.navigation.ROUT_ADD
import com.moch.touchbfsuma.navigation.ROUT_HOME
import com.moch.touchbfsuma.navigation.ROUT_ACCOUNT
import com.moch.touchbfsuma.navigation.ROUT_STOCK
import com.moch.touchbfsuma.ui.theme.themeBlue

import com.moch.touchbfsuma.utils.StockCard
import com.moch.touchbfsuma.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StocksScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == 1,
                            onClick = {

                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            })
                    }
                }
            },


            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(ROUT_ADD)
                }) {
                    IconButton(onClick = {
                        navController.navigate(ROUT_ADD)
                    }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "menu")
                    }
                }
            },


        //Content Section
        content = @Composable {
            val pvm= ProductViewModel()
            var fetchedData by remember { mutableStateOf<List<Product>?>(null) }
            var errorMessage by remember { mutableStateOf<String?>(null) }
            val mContext = LocalContext.current
            LaunchedEffect(Unit) {
                pvm.fetchData("Product_Details", context =mContext ) { data, error ->
                    if (error != null) {
                        Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show()
                        errorMessage = error
                    } else {
                        Toast.makeText(mContext,"Success",Toast.LENGTH_SHORT).show()
                        fetchedData = data
                    }
                }
            }
            if (fetchedData != null) {
                // Display fetched data
                // For example, you can use a LazyColumn to display a list of items
                LazyColumn {
                    itemsIndexed(fetchedData!!) { index, item ->
//                // Composable to display each item in the list
                        var uName:String = ""
                        var uPrice:Int = 0
                        var uInit:Int = 0
                        var uUrl:String = ""
                        fetchedData!![index].name.let {
                            it?.let { it1 ->
                              uName = it1
                            }}
                        fetchedData!![index].price.let {
                            it?.let { it1 ->
                                uPrice = it1
                            }}
                        fetchedData!![index].amount.let {
                            it?.let { it1 ->
                                uInit = it1
                            }}
                        fetchedData!![index].ImageUrl.let {
                            it?.let { it1 ->
                              uUrl = it1
                            }}
                        StockCard(name = uName, price = uPrice, initial = uInit, imageUrl = uUrl)

                    }


                }
            } else if (errorMessage != null) {
                // Display error message
                Text(text = errorMessage!!)
            } else {
                // Loading indicator or placeholder UI while data is being fetched
                CircularProgressIndicator()
            }
        }
        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route=ROUT_HOME,
        selectedIcon= Icons.Filled.Home,
        unselectedIcon= Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),



    BottomNavItem(
        title = "Stock",
        route=ROUT_STOCK,
        selectedIcon= Icons.Filled.Menu,
        unselectedIcon= Icons.Outlined.Menu,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Sell",
        route= ROUT_ACCOUNT,
        selectedIcon= Icons.Filled.AccountCircle,
        unselectedIcon= Icons.Outlined.AccountCircle,
        hasNews = false,
        badges=0
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon : ImageVector,
    val hasNews :Boolean,
    val badges :Int
)
@Preview
@Composable
fun StocksPreview(){
    StocksScreen(rememberNavController())
}

