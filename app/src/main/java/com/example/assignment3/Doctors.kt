package com.example.assignment3
data class Doctor(
    val name: String,
    val specialty: String,
    val symptoms: List<String>,
    val rating: Double,
    val address: String
)

val doctors = listOf(
    Doctor(name = "Dr. Smith", specialty = "Cardiologist", symptoms = listOf("chest pain", "shortness of breath"), rating = 4.5, address = "123 Main St, City, State"),
    Doctor(name = "Dr. Johnson", specialty = "Dermatologist", symptoms = listOf("rash", "itching"), rating = 4.0, address = "213 Main St, City, State"),
    Doctor(name = "Dr. Alex", specialty = "Dermatologist", symptoms = listOf("rash", "itching"), rating = 4.2, address = "123 Main St, City, State"),
    Doctor(name = "Dr. John", specialty = "Dermatologist", symptoms = listOf("rash", "itching"), rating = 3.8, address = "123 Main St, City, State"),
    Doctor(name = "Dr. Lex", specialty = "Dermatologist", symptoms = listOf("rash", "itching"), rating = 4.7, address = "123 Main St, City, State"),
    Doctor(name = "Dr. Robert", specialty = "Dermatologist", symptoms = listOf("rash", "itching"), rating = 4.1, address = "123 Main St, City, State"),
    Doctor(name = "Dr. Williams", specialty = "Gastroenterologist", symptoms = listOf("stomach pain", "nausea"), rating = 4.3, address = "123 Main St, City, State")
)
