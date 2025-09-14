package com.azfar.nocts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.activity.compose.LocalActivity
import androidx.compose.ui.tooling.preview.Preview


class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ForgotPasswordScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ForgotPasswordScreen(modifier: Modifier = Modifier) {
    var icNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) } // controls popup visibility
    val activity = LocalActivity.current
    val customYellow = Color(0xFFD79A18)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
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
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Forgot Password",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-150).dp)
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-35).dp)
            ) {
                OutlinedTextField(
                    value = icNumber,
                    onValueChange = { icNumber = it },
                    label = { Text("MyKad Number") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Reset Password Button
                Button(
                    onClick = {
                        // Instead of real reset logic, just show the popup
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customYellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Reset Password")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Back Button
                Button(
                    onClick = { activity?.finish() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customYellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Back")
                }
            }
        }
    }

// 🔹 Popup Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Password Recovery") },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Recovery steps have been sent to the email associated to the account.",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Centered Continue Button
                    Button(
                        onClick = {
                            showDialog = false
                            // Optional: also return to Login
                            // activity?.finish()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = customYellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth(0.6f) // 60% width, centered
                    ) {
                        Text("Continue")
                    }
                }
            },
            confirmButton = {}, // remove default button placement
            dismissButton = {}  // no dismiss button
        )
    }}


@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    NOCTSTheme {
        ForgotPasswordScreen()
    }
}
