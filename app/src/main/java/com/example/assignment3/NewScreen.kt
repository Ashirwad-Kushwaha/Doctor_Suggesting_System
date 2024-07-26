package com.example.assignment3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewScreen(navController: NavController) {
    var patientName by remember { mutableStateOf("") }
    var symptoms by remember { mutableStateOf(mutableListOf<String>()) } // changed to list
    var showSnackbar by remember { mutableStateOf(false) }
    val symptomOptions = doctors.flatMap { it.symptoms }.distinct()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Doctor Suggesting System!")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = patientName,
            onValueChange = { patientName = it },
            label = { Text("Patient Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            symptomOptions.forEach { symptom ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = symptoms.contains(symptom),
                        onClick = {
                            if (symptoms.contains(symptom)) {
                                symptoms.remove(symptom) // remove if already selected
                            } else {
                                symptoms.add(symptom) // add if not selected
                            }
                        }
                    )
                    Text(
                        text = symptom,
                        style = MaterialTheme.typography.bodyMedium.copy(color = if (symptoms.contains(symptom)) Color.Green else Color.Black), // change color if selected
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (patientName.isNotBlank() && symptoms.isNotEmpty()) {
                val recommendedDoctors = doctors.filter { doctor -> symptoms.any { symptom -> doctor.symptoms.contains(symptom) } }
                val doctorNames = recommendedDoctors.joinToString(",") { it.name }
                navController.navigate("result_screen/$doctorNames/$patientName")
            } else {
                showSnackbar = true
            }
        }) {
            Text(text = "Suggest Doctor")
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
