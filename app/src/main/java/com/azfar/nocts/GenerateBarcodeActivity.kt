package com.azfar.nocts

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme

class GenerateBarcodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GenerateBarcodeScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLogout = {
                            // Navigate to LoginActivity and clear stack
                            val intent = android.content.Intent(this, LoginActivity::class.java)
                            intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GenerateBarcodeScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = context as? Activity
    var showExitDialog by remember { mutableStateOf(false) }

    // Handle back press to show confirmation dialog
    BackHandler {
        showExitDialog = true
    }

    // Exit confirmation dialog
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Exit App") },
            text = { Text("Are you sure you want to exit the app?") },
            confirmButton = {
                TextButton(onClick = {
                    showExitDialog = false
                    activity?.finishAffinity() // exits app completely
                }) {
                    Text("Yes", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Logo
            Spacer(modifier = Modifier.height(0.dp))
            Image(
                painter = painterResource(id = R.drawable.onlylogo),
                contentDescription = "Logo",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Instruction
            Text(
                text = "Here is your generated barcode.",
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Barcode image
            Image(
                painter = painterResource(id = R.drawable.barcode),
                contentDescription = "Barcode",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Logout button
            Button(
                onClick = { onLogout() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenerateBarcodePreview() {
    NOCTSTheme {
        GenerateBarcodeScreen()
    }
}

