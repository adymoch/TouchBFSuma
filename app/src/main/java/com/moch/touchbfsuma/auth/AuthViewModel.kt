package com.moch.touchbfsuma.auth

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.moch.touchbfsuma.data.User
import com.moch.touchbfsuma.navigation.ROUT_HOME
import com.moch.touchbfsuma.navigation.ROUT_LOGIN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel (var navController: NavHostController, var context: Context) {
    val mAuth: FirebaseAuth
    val progress: ProgressDialog
    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    fun login(context: Context, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful, navigate to next screen or perform desired action
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()

                    navController.navigate(ROUT_HOME)
                } else {
                    // Login failed, display error message
                    Toast.makeText(this.context, task.exception?.message, Toast.LENGTH_SHORT).show()

                }
            }

    }

    fun signUp(context: Context, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign up successful
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_HOME)

                } else {
                    // Sign up failed with error message
                    Toast.makeText(this.context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun saveUserData(userData : User, context: Context)=
        CoroutineScope(Dispatchers.IO).launch{
            val fireStoreRef = Firebase.firestore
                .collection("users")
                .document(userData.userEmail)
            try {
                fireStoreRef.set(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context,"Successfuly Added",Toast.LENGTH_SHORT).show()
                    }

            }catch (e : Exception){
                Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
            }

        }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUT_LOGIN)
    }

    fun isLoggedIn():Boolean{
        return mAuth.currentUser != null
    }

    fun uuid():String{
        return mAuth.currentUser.toString()
    }

}