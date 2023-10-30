package com.zte.iptvclient.android.idmnc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zte.iptvclient.android.auth.VPAuth
import com.zte.iptvclient.android.auth.utils.Result
import com.zte.iptvclient.android.idmnc.ui.theme.VisionplusbssandroidTheme

class MainActivity : ComponentActivity() {
    val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode) {
            Result.SUCCESS -> {
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
                Surface(color = MaterialTheme.colorScheme.background) {
                    VPAuth.forgotPassword(this, activityForResult)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VisionplusbssandroidTheme {
        Greeting("Android")
    }
}