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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp
import androidx.activity.compose.LocalActivity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext


class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignupScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SignupScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)) // Light grey background
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo Image (centered at top)
            Image(
                painter = painterResource(id = R.drawable.onlylogo), // Replace 'logo' with your image file name
                contentDescription = "NOCTS Logo",
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .offset(y = (-150).dp) // Moves it up
                    .align(Alignment.CenterHorizontally)
            )


            Text(
                text = "Signup",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF101010),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-150).dp) // Moves it up)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        // TODO: Handle forgot password click
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-35).dp) // Adjust this value to move everything upward
            ) {
                var myKad by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = myKad,
                    onValueChange = { myKad = it },
                    label = { Text("Mykad") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,

                )

                Spacer(modifier = Modifier.height(8.dp))



                Spacer(modifier = Modifier.height(32.dp))


                val customYellow = Color(0xFFD79A18)

                Button(
                    onClick = {
                        val intent = Intent(context, SignupActivity2::class.java)
                        context.startActivity(intent)
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

                Spacer(modifier = Modifier.height(12.dp))

                val activity = LocalActivity.current


                Button(
                    onClick = {
                        activity?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD79A18),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Back")
                }



            }}
    }
}

@Preview(showBackground = true)
@Composable
fun SignupActivityScreenPreview() {
    NOCTSTheme {
        SignupScreen()
    }
}
