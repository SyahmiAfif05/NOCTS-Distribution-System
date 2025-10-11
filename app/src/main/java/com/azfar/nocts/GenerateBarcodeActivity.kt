package com.azfar.nocts

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
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GenerateBarcodeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // light gray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(0.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.onlylogo), // replace with your logo
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Station name

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
                painter = painterResource(id = R.drawable.barcode), // place barcode PNG in drawable
                contentDescription = "Barcode",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))


        }

        // Back button at bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



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
