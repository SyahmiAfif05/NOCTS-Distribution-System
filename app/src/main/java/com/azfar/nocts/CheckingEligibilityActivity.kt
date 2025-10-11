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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azfar.nocts.ui.theme.NOCTSTheme

class CheckingEligibilityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckingEligibilityScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CheckingEligibilityScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // light grey background
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

            // Title
            Text(
                text = "Checking your eligibility…",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = (-70).dp)
            )

            // Info text
            Text(
                text = "Please wait while we verify your details.\n" +
                        "This may take a few seconds.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 18.sp,
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
                    context.startActivity(Intent(context, SubsidyEligibilityStatusActivity::class.java))
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
fun CheckingEligibilityPreview() {
    NOCTSTheme {
        CheckingEligibilityScreen()
    }
}

        Greeting5("Android")
    }
}
