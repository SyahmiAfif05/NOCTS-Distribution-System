package com.azfar.nocts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.activity.compose.rememberLauncherForActivityResult

class DeclaringPayslipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeclaringPayslipScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DeclaringPayslipScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedFile by remember { mutableStateOf<Uri?>(null) }

    // File picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFile = uri
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // same grey background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo Image (same style as login)
            Image(
                painter = painterResource(id = R.drawable.onlylogo),
                contentDescription = "NOCTS Logo",
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .offset(y = (-200).dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Title
            Text(
                text = "Declare Payslip",
                textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-250).dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Please submit the most recent payslip that you have received.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-200).dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "(Payslip must be in PDF format)",
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
                    .offset(y = (-35).dp) // move upward like login screen
            ) {
                val customYellow = Color(0xFFD79A18)

                // Submit Payslip button
                Button(
                    onClick = { launcher.launch("application/pdf") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customYellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(if (selectedFile == null) "Submit Payslip" else "Payslip Selected")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Continue button (disabled until payslip submitted)
                Button(
                    onClick = {
                        context.startActivity(Intent(context, ResubmitPayslipActivity::class.java))
                    },
                    enabled = selectedFile != null, // disable if not submitted
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customYellow,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.DarkGray
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
}

@Preview(showBackground = true)
@Composable
fun DeclaringPayslipActivityPreview() {
    NOCTSTheme {
        DeclaringPayslipScreen()
    }
}

