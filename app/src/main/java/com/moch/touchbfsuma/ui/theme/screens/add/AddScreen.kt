package com.moch.touchbfsuma.ui.theme.screens.add

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.ImageDecoder.*
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.moch.touchbfsuma.data.Product
import com.moch.touchbfsuma.navigation.ROUT_HOME
import com.moch.touchbfsuma.ui.theme.Pink40
import com.moch.touchbfsuma.ui.theme.themeBlue
import com.moch.touchbfsuma.viewmodel.ProductViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navHostController: NavHostController){

    var productName by remember { mutableStateOf("") }
    var productDesc by remember { mutableStateOf("") }
    var productAmount by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val productViewModel = ProductViewModel()
    val mContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ){

        //Top App Bar for Adding A product
        TopAppBar(
            title = { Text(text = "Add a Product",
                color = Color.White)},
            colors = TopAppBarDefaults.mediumTopAppBarColors(themeBlue),
            navigationIcon = {
                IconButton(onClick = {
                    navHostController.navigate(ROUT_HOME)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                }
            },
            actions = {
                IconButton(onClick = {}) {


                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "ArrowBack",
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                }


                Spacer(modifier = Modifier.width(20.dp))


            })
        //End Top App Bar

        //Product Details
        //An image of thr product
        //The Name
        //The product Code
        //The Price
        //The Remaining Quantity

        //============================// body of the screen

        //Image Picker
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ){

            var imageUri by remember { mutableStateOf<Uri?>(null)  }
            val mContext = LocalContext.current
            var bitmap = remember { mutableStateOf<Bitmap?>(null) }
            val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri

            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                imageUri?.let {
                    if(Build.VERSION.SDK_INT < 28){
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(mContext.contentResolver, it)

                    }else{
                        val source = createSource(mContext.contentResolver, it)
                        bitmap.value = decodeBitmap(source)
                    }

                    bitmap.value?.let{btm ->

                        Image(
                            bitmap = btm.asImageBitmap() ,
                            contentDescription = null ,
                            modifier = Modifier
                                .size(400.dp)
                                .padding(20.dp)
                        )

                        val storage = FirebaseStorage.getInstance()
                        val imagesRef = storage.reference.child(auth.currentUser?.uid.toString())
                        val imageRef = imagesRef.child("image.jpg")
                        try {


                            imageRef.putFile(imageUri!!)
                                .addOnSuccessListener {taskSnapshot ->
                                    // Upload successful actions (e.g., toast message)
                                    val downloadUrl = taskSnapshot.storage.downloadUrl
                                    downloadUrl.addOnCompleteListener { downloadTask ->
                                        if (downloadTask.isSuccessful) {
                                            val url = downloadTask.result
                                            productImage = url.toString()


                                        } else {
                                            // Handle download URL retrieval error (optional)
                                        }
                                    }
//Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show()

                                }
                                .addOnFailureListener { exception ->
                                    // Upload failure actions (e.g., error message)
                                    Toast.makeText(mContext, exception.toString(), Toast.LENGTH_SHORT).show()
                                }

                        } catch (_: Exception) {

                        } finally {

                        }


                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        launcher.launch("image/*")
                    }

                ) {
                    Text(text = "Pick Image")
                }

            }

        }


        //Fields

        Column (

        ){

            //Text field
            TextField(
                value = productName,
                onValueChange = {productName = it},
                placeholder = { Text(text="Enter The Product Name") },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

            )
            Spacer(modifier = Modifier.height(10.dp))

            //End Text field

            //Text Field
            TextField(
                value = productDesc,
                onValueChange = {productDesc = it},
                placeholder = { Text(text="Enter The Product Description") },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

            )
            Spacer(modifier = Modifier.height(10.dp))

            //End Text field

            //Text Field
            TextField(
                value = productPrice,
                onValueChange = {productPrice = it},
                placeholder = { Text(text="Enter The Product Price") },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Info")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(10.dp))

            //End Text field

            //Text Field
            TextField(
                value = productAmount,
                onValueChange = {productAmount = it},
                placeholder = { Text(text="Enter The Amount of Items") },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Info")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(10.dp))

            //End Text field
        }


        //Button to add / update
Button(
    onClick = {
              val prod = Product(
                  name = productName,
                  ImageUrl = productImage,
                  Description = productDesc,
                  amount = productAmount.toInt(),
                  price = productPrice.toInt(),
                  UserId = auth.currentUser?.uid.toString()
              )

productViewModel.saveData(prod,mContext)
    },
    modifier =
Modifier.fillMaxWidth()
    .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(5.dp),


    ) {
    Text(text = "Add")
}
        //============================//

    }

}



@Preview
@Composable
fun AddScreenPreview(){
    AddScreen(rememberNavController())

}