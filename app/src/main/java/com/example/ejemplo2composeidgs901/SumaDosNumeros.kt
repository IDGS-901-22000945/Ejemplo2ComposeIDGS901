package com.example.ejemplo2composeidgs901

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SumaDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacionSeleccionada by remember { mutableStateOf("Suma") }

    val operaciones = listOf("Suma", "Resta", "Multiplicación", "División")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Ingrese el primer número") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Ingrese el segundo número") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text("Selecciona la operación:")

        operaciones.forEach { operacion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = operacion == operacionSeleccionada,
                        onClick = { operacionSeleccionada = operacion }
                    )
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = operacion == operacionSeleccionada,
                    onClick = { operacionSeleccionada = operacion }
                )
                Text(text = operacion)
            }
        }

        Button(onClick = {
            val n1 = num1.toFloatOrNull()
            val n2 = num2.toFloatOrNull()
            resultado = if (n1 != null && n2 != null) {
                when (operacionSeleccionada) {
                    "Suma" -> "Resultado: ${n1 + n2}"
                    "Resta" -> "Resultado: ${n1 - n2}"
                    "Multiplicación" -> "Resultado: ${n1 * n2}"
                    "División" -> if (n2 != 0f) "Resultado: ${n1 / n2}" else "No se puede dividir entre cero"
                    else -> "Operación no válida"
                }
            } else {
                "Ingrese números válidos"
            }
        }) {
            Text("Calcular")
        }

        Text(text = resultado)
    }
}