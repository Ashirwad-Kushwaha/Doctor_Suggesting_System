package com.example.assignment3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.doctors
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.text.format.DateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(doctorName: String?) {
    val doctor = doctors.find { it.name == doctorName }
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog) {
        if (name.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Booking Successful") },
                text = { Text("Patient Name: $name\nDoctor Name: ${doctor?.name}\nDoctor Address: ${doctor?.address}\nDate: $date\nTime: $time") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        } else {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Error") },
                text = { Text("All fields must be filled out before booking an appointment.") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Book an appointment with ${doctor?.name}", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
        Text("Address: ${doctor?.address}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(context, { _, year, month, dayOfMonth ->
                    date = "$dayOfMonth/${month + 1}/$year"
                }, year, month, day).show()
            }) {
                Text("Select Date")
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = date,
                onValueChange = {},
                label = { Text("Selected Date") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                TimePickerDialog(context, { _, hourOfDay, minute ->
                    time = "$hourOfDay:$minute"
                }, hour, minute, DateFormat.is24HourFormat(context)).show()
            }) {
                Text("Select Time")
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = time,
                onValueChange = {},
                label = { Text("Selected Time") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Book Appointment")
        }
    }
}
