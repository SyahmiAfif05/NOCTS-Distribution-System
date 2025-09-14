package com.azfar.nocts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.compose.ui.text.font.FontWeight
import android.content.Intent
import androidx.compose.ui.platform.LocalContext


class LoginSignupPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFECECEC)
                ) {
                    LoginSignupScreen()
                }
            }
        }
    }
}

@Composable
fun LoginSignupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding( 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.onlylogo), // Replace with your image resource
            contentDescription = "NOCTS Logo",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .offset(y = (-200).dp) // Moves it up

        )

        // Title
        Text(
            text = "Welcome to NOCTS",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF101010),
            modifier = Modifier
                .padding(bottom = 32.dp, top = 32.dp)
                .offset(y = (-150).dp) // Moves it up
        )

        // Login Button
        val context = LocalContext.current

        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD79A18),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp)
        ) {
            Text("Login")
        }


        // Sign Up Button
        Button(
            onClick = {
                val intent = Intent(context, SignupActivity::class.java)
                context.startActivity(intent)
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD79A18),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginSignupPreview() {
    NOCTSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFECECEC)
        ) {
            LoginSignupScreen()
        }
    }
}
