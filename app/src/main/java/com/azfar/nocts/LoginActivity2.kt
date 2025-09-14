package com.azfar.nocts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.compose.ui.platform.LocalContext

class LoginActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen2(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // Light grey background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo
            Image(
                painter = painterResource(id = R.drawable.onlylogo),
                contentDescription = "NOCTS Logo",
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .offset(y = (-150).dp)
            )

            // Instruction text
            Text(
                text = "Before we get started, we need a bit of information about you to determine your eligibility.",
                color = Color(0xFF101010),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 32.dp)
                    .offset(y = (-100).dp)

            )

            val customYellow = Color(0xFFD79A18)
            val context = LocalContext.current

            Button(
                onClick = {
                    context.startActivity(Intent(context, DeclaringPayslipActivity::class.java))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = customYellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivity2Preview() {
    NOCTSTheme {
        LoginScreen2()
    }
}