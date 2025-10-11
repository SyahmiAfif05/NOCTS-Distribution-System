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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme

class SubsidyEligibilityStatusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SubsidyEligibilityStatusScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SubsidyEligibilityStatusScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // grey background
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
                    .height(200.dp)
                    .width(200.dp)
                    .offset(y = (-120).dp)
            )

            // Title in green
            Text(
                text = "Subsidy eligibility status",
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF2E7D32), // green
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = (-70).dp)
            )

            // Body
            Text(
                text = "Congratulations!\n\n" +
                        "You are determined to be eligible for the petrol subsidy!\n\n" +
                        "You will receive generated barcode for subsidy allocation.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }

        // Continue button at bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val customYellow = Color(0xFFD79A18)

            Button(
                onClick = {
                    val intent = Intent(context, GenerateBarcodeActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = customYellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 24.dp)
            ) {





















                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubsidyEligibilityStatusPreview() {
    NOCTSTheme {
        SubsidyEligibilityStatusScreen()
    }
}
