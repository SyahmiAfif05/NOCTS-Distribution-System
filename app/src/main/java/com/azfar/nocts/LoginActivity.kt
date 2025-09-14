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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azfar.nocts.ui.theme.NOCTSTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp
import androidx.activity.compose.LocalActivity
import androidx.compose.ui.text.font.FontWeight
import android.content.Intent
import androidx.compose.ui.platform.LocalContext



class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOCTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
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
                text = "Login",
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
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = myKad,
                onValueChange = { myKad = it },
                label = { Text("MyKad Number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

                val context = LocalContext.current

                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF101010),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 4.dp)
                        .clickable {
                            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
                        }
                )



            Spacer(modifier = Modifier.height(32.dp))


            val customYellow = Color(0xFFD79A18)

            Button(
                onClick = {
                    context.startActivity(Intent(context, LoginActivity2::class.java))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = customYellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Login")
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
fun LoginScreenPreview() {
    NOCTSTheme {
        LoginScreen()
    }
}
