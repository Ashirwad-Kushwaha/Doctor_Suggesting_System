package com.example.assignment3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.Doctor
import com.example.assignment3.doctors

@Composable
fun ResultScreen(doctorNames: String?, patientName: String?, navController: NavController) {
    val doctorNamesList = doctorNames?.split(",") ?: emptyList()
    val matchingDoctors = doctors.filter { doctor -> doctorNamesList.contains(doctor.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Patient Name: $patientName", style = MaterialTheme.typography.labelLarge)

        Text("Suggested doctors:", style = MaterialTheme.typography.labelLarge)

        Spacer(modifier = Modifier.height(16.dp))
        10

        if (matchingDoctors.isNotEmpty()) {
            LazyColumn {
                items(matchingDoctors) { doctor ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("appointment/${doctor.name}") }
                            .padding(8.dp),
                        border = BorderStroke(width = 1.dp, color = Color.Black),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        DoctorInfo(doctor)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            Text("No doctors found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun DoctorInfo(doctor: Doctor) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = doctor.name, style = MaterialTheme.typography.headlineLarge)
        Text(text = doctor.specialty, style = MaterialTheme.typography.bodyLarge)
        Text(text = doctor.address, style = MaterialTheme.typography.bodyLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Rating: ${doctor.rating}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = doctor.rating.toString(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
