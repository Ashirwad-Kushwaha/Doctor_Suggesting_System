package com.example.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.example.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                val navController = rememberNavController()
                NavHost(navController= navController,startDestination = "login_screen"){
                    composable("login_screen"){ LoginScreen(navController)}
                    composable("new_screen"){ NewScreen(navController)}
                    composable("result_screen/{doctorNames}/{patientName}"){backStackEntry ->
                        ResultScreen(
                            doctorNames = backStackEntry.arguments?.getString("doctorNames"),
                            patientName = backStackEntry.arguments?.getString("patientName"),
                            navController = navController
                        )}
                    composable("appointment/{name}") { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name")
                        AppointmentScreen(name)
                    }

                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
//                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)) // Set border radius
                .background(MaterialTheme.colorScheme.primary) // Set box color
                .border(5.dp, MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Doctor Suggesting",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 30.sp,
                        color = Color.White
                    ),
                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "System",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 30.sp,
                        color = Color.White
                    ),
                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 30.dp)
                )
            }
        }
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 30.sp),
            modifier = Modifier.padding(all = 16.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = passwordVisibility,
                onCheckedChange = { passwordVisibility = it },
            )
            Text(text = "Show password", modifier = Modifier.padding(start = 8.dp))
        }


        Button(onClick = {
            if (username.isNotBlank() && password.isNotBlank()) {
                navController.navigate("new_screen")
            } else {
                showSnackbar = true
            }
        }) {
            Text(text = "Login")
        }

        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { showSnackbar = false }) {
                        Text(text = "Dismiss")
                    }
                }
            ) {
                Text(text = "Please fill in all fields.")
            }
        }
    }
}