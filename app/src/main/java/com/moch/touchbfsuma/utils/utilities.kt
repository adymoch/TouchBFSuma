package com.moch.touchbfsuma.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.moch.touchbfsuma.R
import com.moch.touchbfsuma.ui.theme.themeBlue


@Composable
fun StockCard(name:String = "",price:Int = 0,initial:Int = 0,current:Int = 0,imageUrl:String ){
    //Image Url
    //Product Name
    //Product Quantity
    //Product Price
Row(
    modifier = Modifier.fillMaxWidth()
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 30.dp, horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = themeBlue, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        )
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f)
            ) {
                AsyncImage(
                    model = imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "background_image",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Name : $name",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(1.dp)
                )
                Text(
                    text = "Price: $price ",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Initial Amount: $initial",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Current Amount: $current",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(2.dp)
                )


            }
        }
    }
}
}
@Preview
@Composable

fun UtilPreview(){
   // StockCard()
}