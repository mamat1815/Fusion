package com.theartemis.fusion.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.theartemis.fusion.R
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.User
import com.theartemis.fusion.data.remote.RemoteDataSource
import com.theartemis.fusion.databinding.ActivitySignInBinding
import com.theartemis.fusion.ui.main.MainActivity
import com.theartemis.fusion.utility.ViewModelFactory

@Suppress("DEPRECATION")
class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataRepository = DataRepository(RemoteDataSource())
        val viewModelFactory = ViewModelFactory(dataRepository)
        viewModel = ViewModelProvider(this,viewModelFactory)[SignInViewModel::class.java]


        binding.apply {
            btnSignIn.setOnClickListener {
                val signInIntent = configureGoogleSignIn().signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    private fun successSignIn(user: User) {
        showToast("Welcome ${user.displayName}")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun configureGoogleSignIn(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.sign_in_token))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let {
                    viewModel.signInWithGoogle(it).observe(this@SignInActivity) {response ->
                        val user = User(uid = response.uid, displayName = response.displayName, profileImg = response.profileImg)
                        successSignIn(user)
                    }

                }
            } catch (e: ApiException) {
                showToast("Google Sign-In failed: ${e.statusCode}")
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }

}