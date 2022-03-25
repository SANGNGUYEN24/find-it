package com.solution_challenge_2022.findit.findit_feature.presentation.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.FragmentSettingBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.User
import com.solution_challenge_2022.findit.findit_feature.presentation.MainViewModel

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val mainViewModel: MainViewModel by activityViewModels()

    // constants
    private companion object {
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        // configure the Google SignIn
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) //don't worry if shows in red, will be resolved when build first time
            .requestEmail() //we only need email from google account
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
        //init firebase auth
        checkUser()

        //Google SignIn Button, Click to begin Google SignIn
        binding.googleSignInButton.setOnClickListener {
            //begin Google SignIn
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            Toast.makeText(context, "Already logged in", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google Signin success, now auth with firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            } catch (e: Exception) {
                // Failed Google Signin
                Log.d(TAG, "onActivityResult: s(e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnSuccessListener { authResult ->
            // Login success
            Log.d(TAG, "firebaseAuthwithGoog leAccount: LoggedIn")

            // Get logged in user
            val firebaseUser = firebaseAuth.currentUser
            // Get user info
            val uid = firebaseUser!!.uid
            val email = firebaseUser.email
            val displayName = firebaseUser.displayName
            val profileUrl = firebaseUser.photoUrl

            mainViewModel.updateUser(
                User(
                    uid = uid,
                    email = email,
                    userName = displayName,
                    profileUrl = profileUrl
                )
            )

            Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
            Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")
            Log.d(TAG, "firebaseAuthWithGoogleAccount: User Name: $displayName")
            Log.d(TAG, "firebaseAuthWithGoogleAccount: PhotoUri: $profileUrl")

            // check if user is new or existing
            if (authResult.additionalUserInfo!!.isNewUser) {
                //user is new - Account created
                Log.d(TAG, "firebaseAuthwithGoogleAccount: Account created... \nsemail")
                Toast.makeText(context, "Account created... \nsemail", Toast.LENGTH_SHORT).show()
            } else {
                //existing user - LoggedIn
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \nsemail")
                Toast.makeText(context, "LoggedIn... \nsemail", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            //login failed
            Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
            Toast.makeText(context, "Loggin Failed due to ${e.message}", Toast.LENGTH_SHORT).show()

        }
    }
}