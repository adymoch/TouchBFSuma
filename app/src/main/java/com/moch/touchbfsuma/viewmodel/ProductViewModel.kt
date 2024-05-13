package com.moch.touchbfsuma.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.moch.touchbfsuma.data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel() {
    fun saveData(
        userData: Product,
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore
            .collection("Product_Details")
            .document(userData.name!!)
        try {
            fireStoreRef.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(context,"Successfuly Added",Toast.LENGTH_SHORT).show()
                }

        }catch (e : Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }

    }
    fun uploadProfilePic(imageUri: Uri?, context: Context, uuid:String) {
        uuid.let {uid ->
            if (imageUri == null) return
            // Firebase storage instance (replace with your initialization)
            val storage = FirebaseStorage.getInstance()

            val imagesRef = storage.reference.child(uid)
            val imageRef = imagesRef.child("profile.jpg")
            val auth = FirebaseAuth.getInstance()

            try {


                imageRef.putFile(imageUri)
                    .addOnSuccessListener {taskSnapshot ->
                        // Upload successful actions (e.g., toast message)
                        val downloadUrl = taskSnapshot.storage.downloadUrl
                        downloadUrl.addOnCompleteListener { downloadTask ->
                            if (downloadTask.isSuccessful) {
                                val url = downloadTask.result
                                val fireStoreRef = Firebase.firestore
                                    .collection("users")
                                val docRef = fireStoreRef.document(auth.currentUser?.email.toString())
                                try {
                                    docRef.update("imageUrl", url)
                                } catch (e: Exception) {

                                }
                                // Use the download URL to display or download the image elsewhere
                            } else {
                                // Handle download URL retrieval error (optional)
                            }
                        }
                        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener { exception ->
                        // Upload failure actions (e.g., error message)
                        Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
                    }

            } catch (_: Exception) {

            } finally {

            }

        }


    }
    fun fetchData(
        collectionPath: String,
        context: Context,
        onDataReceived: (List<Product>?, String?) -> Unit) {
        val firestore = Firebase.firestore
        firestore.collection(collectionPath)
            .get()
            .addOnSuccessListener { result ->
                val dataList = mutableListOf<Product>()
                for (document in result) {
                    val data = document.toObject(Product::class.java)
                    dataList.add(data)
                }
                onDataReceived(dataList, null)

            }

            .addOnFailureListener { e ->
                onDataReceived(null, e.message)


            }
    }

}