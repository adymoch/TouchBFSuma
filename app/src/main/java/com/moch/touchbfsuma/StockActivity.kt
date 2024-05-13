package com.moch.touchbfsuma

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moch.touchbfsuma.data.Product
import com.moch.touchbfsuma.ui.theme.TouchBFSumaTheme
import com.moch.touchbfsuma.viewmodel.ProductViewModel

class StockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayScreen()
        }
    }
}

@Composable
fun DisplayScreen() {
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

                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))


                        Column {

                            Text(
                                text = "Product Name :",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            fetchedData!![index].name.let {
                                it?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier.padding(4.dp),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                }
                            }

                            Text(
                                text = "Product Description :",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            fetchedData!![index].Description.let {
                                it?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier.padding(4.dp),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                }
                            }

                            Text(
                                text = "Product Description :",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            fetchedData!![index].ImageUrl.let {
                                it?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier.padding(4.dp),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                }
                            }

                            Text(
                                text = "Product Shop :",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            fetchedData!![index].UserId.let {
                                it?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier.padding(4.dp),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                }
                            }
                        } //Row with Data

                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }//End Card

//
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

