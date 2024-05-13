package com.moch.touchbfsuma.ui.theme.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Add
import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moch.touchbfsuma.navigation.ROUT_ADD
import com.moch.touchbfsuma.ui.theme.themeBlue
import com.moch.touchbfsuma.R
import com.moch.touchbfsuma.StockActivity
import com.moch.touchbfsuma.navigation.ROUT_ACCOUNT
import com.moch.touchbfsuma.navigation.ROUT_HOME
import com.moch.touchbfsuma.navigation.ROUT_STOCK


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
val mContext = LocalContext.current
        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
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
            content = @Composable{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //Home Page

                    //A column at the top followed by some cards

                    //Column

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(themeBlue)
                    ){

                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.img),
                                contentDescription = "background_image",
                                contentScale = ContentScale.FillBounds
                            )

                            Text(
                                text = "Touch BF Suma",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }

                    }



                    //Cards

                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(vertical = 30.dp, horizontal = 20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = themeBlue, //Card background color
                                contentColor = Color.White  //Card content color,e.g.text
                            )

                        ){
                            Row (
                                modifier = Modifier.fillMaxSize()
                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(vertical = 4.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                    Text(
                                        text = "The",
                                        fontSize = 25.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "Most Popular",
                                        fontSize = 25.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium

                                        )
                                    Text(
                                        text = "Product",
                                        fontSize = 25.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium
                                    )

                                }//End Column
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),


                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight(0.5f)
                                            .fillMaxWidth()
                                            .padding(top = 10.dp),

                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Body Spray",
                                            fontSize = 15.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Medium
                                        )

                                } //First Row
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()

                                    ) {
                                        Text(
                                            text = "20 pieces",
                                            fontSize = 30.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(8.dp))
                                    } //Second Row

                                }

                            }

                        }

                    }

                    HorizontalDivider(thickness = 2.dp, color = Color.Black)
                    Spacer(modifier = Modifier.height(20.dp))



                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ){

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight()
                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = themeBlue, //Card background color
                                    contentColor = Color.White  //Card content color,e.g.text
                                )

                            ){
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){


                                    Text(
                                        text = "Total Items",
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(8.dp))
                                }
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "15",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(8.dp)
                                        )
                                }
                                }

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()

                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = themeBlue, //Card background color
                                    contentColor = Color.White  //Card content color,e.g.text
                                )

                            ){
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){


                                    Text(
                                        text = "Add New Item",
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(8.dp))
                                }
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.baseline_add_24),
                                        contentDescription = "Add" ,
                                        modifier = Modifier.size(40.dp))
                                }
                            }
                        }

                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ){

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight()
                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = themeBlue, //Card background color
                                    contentColor = Color.White  //Card content color,e.g.text
                                )

                            ){

                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Sell",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }

                        }

                    }

                    /*
                    * Total Item types
                    *Add New Item
                    *Popular Item
                    *
                    *  */

                }

            }

        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route=ROUT_HOME,
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),



    BottomNavItem(
        title = "Stock",
        route=ROUT_STOCK,
        selectedIcon=Icons.Filled.Menu,
        unselectedIcon=Icons.Outlined.Menu,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Account",
        route=  ROUT_ACCOUNT,
        selectedIcon=Icons.Filled.AccountCircle,
        unselectedIcon=Icons.Outlined.AccountCircle,
        hasNews = false,
        badges=0
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)




@Preview
@Composable
fun HomePreview(){
    HomeScreen(rememberNavController())
}