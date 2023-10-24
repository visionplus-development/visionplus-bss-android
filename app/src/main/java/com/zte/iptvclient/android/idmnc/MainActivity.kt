package com.zte.iptvclient.android.idmnc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zte.iptvclient.android.auth.VPAuth
import com.zte.iptvclient.android.auth.utils.Result
import com.zte.iptvclient.android.idmnc.ui.theme.VisionplusbssandroidTheme

class MainActivity : ComponentActivity() {

    var loginResult = ""
    val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode) {
            Result.SUCCESS -> {
                loginResult = it.data?.extras?.getString("token") ?: "token null"
            }
            Result.CANCELLED -> {
            }
            Result.FAILED -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisionplusbssandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Login("Login", loginResult) {
                        VPAuth.login(this, activityForResult)
                    }
                }
            }
        }
    }
}

@Composable
fun Login(text: String, result: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onClick) {
            Text(text = text)
        }

        Text(text = result)
    }
}