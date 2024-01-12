package com.theartemis.fusion.data.remote

import android.net.Uri
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.theartemis.fusion.data.DataSource
import com.theartemis.fusion.data.Post
import com.theartemis.fusion.data.User
import kotlinx.coroutines.tasks.await
import java.util.UUID

class RemoteDataSource {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    private val storage = FirebaseStorage.getInstance().reference

    interface SignInWithGoogleCallback{
        fun onResponse(response: User)
    }

    interface AddPostCallback{
        fun onResponse(response: Post)
    }

    interface FetchPostCallback {
        fun onResponse(response: List<Post>)
    }


    fun signInWithGoogle(idToken: String, callback: SignInWithGoogleCallback){
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential).addOnCompleteListener {
            val users = auth.currentUser
            val userData = User(users?.uid ?: "",
                users?.displayName ?: "",
                users?.photoUrl?.toString() ?:"")
            db.reference.child("users").child(users?.uid.toString()).setValue(userData)
            callback.onResponse(userData)
        }
    }

    fun addPost(spinner: String, title: String, description: String,imageUri: Uri, callback: AddPostCallback) {

        val dbId = db.reference.child("posts").push().key.toString()
        val userId = auth.currentUser?.uid.toString()
        val username = auth.currentUser?.displayName!!
        val dbReference = db.getReference("posts")
        val storageRef = storage.child("images/${UUID.randomUUID()}.jpg")
        val uploadTask = storageRef.putFile(imageUri)
        val userImg = auth.currentUser?.photoUrl.toString()

        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                val data = Post(dbId,userId,spinner, title, description, imageUrl,username,userImg)

                dbReference.push().setValue(data)
                callback.onResponse(data)
            }
        }
    }

    fun fetchPost(callback: FetchPostCallback) {
        db.getReference("posts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = mutableListOf<Post>()

                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue(Post::class.java)
                    post?.let {
                        postList.add(it)
                    }
                }

                callback.onResponse(postList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}