package pe.edu.idat.jair

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@Composable
fun MenuPrincipal(onEjercicioSeleccionado: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Menú Principal", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onEjercicioSeleccionado(1) }) {
            Text("Ejercicio 1: Estacionamiento")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onEjercicioSeleccionado(2) }) {
            Text("Ejercicio 2: Promedio de notas")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onEjercicioSeleccionado(3) }) {
            Text("Ejercicio 3: DNI")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onEjercicioSeleccionado(4) }) {
            Text("Ejercicio 4: Números pares")
        }
    }
}

@Composable
fun MyApp() {
    var pantallaActual by remember { mutableStateOf(0) }

    when (pantallaActual) {
        0 -> MenuPrincipal(onEjercicioSeleccionado = { pantallaActual = it })
        1 -> Ejercicio1Screen(onVolver = { pantallaActual = 0 })
        2 -> Ejercicio2Screen(onVolver = { pantallaActual = 0 })
        3 -> Ejercicio3Screen(onVolver = { pantallaActual = 0 })
        4 -> Ejercicio4Screen(onVolver = { pantallaActual = 0 })
    }
}

@Composable
fun Ejercicio1Screen(onVolver: () -> Unit) {
    var horas by remember { mutableStateOf("") }
    var minutos by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var mostrarModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Estacionamiento")
        TextField(value = horas, onValueChange = { horas = it }, label = { Text("Horas") })
        TextField(value = minutos, onValueChange = { minutos = it }, label = { Text("Minutos") })
        Button(onClick = {
            val costo = calcularPago(horas.toInt(), minutos.toInt())
            resultado = "El costo es: $costo"
            mostrarModal = true
        }) {
            Text("Calcular pago")
        }
        Button(onClick = onVolver) {
            Text("Volver")
        }

        if (mostrarModal) {
            AlertDialog(
                onDismissRequest = { mostrarModal = false },
                title = { Text(text = "Resultado") },
                text = { Text(text = resultado) },
                confirmButton = {
                    TextButton(onClick = { mostrarModal = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
fun Ejercicio2Screen(onVolver: () -> Unit) {
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var nota4 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var mostrarModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Promedio de notas")
        TextField(value = nota1, onValueChange = { nota1 = it }, label = { Text("Nota 1") })
        TextField(value = nota2, onValueChange = { nota2 = it }, label = { Text("Nota 2") })
        TextField(value = nota3, onValueChange = { nota3 = it }, label = { Text("Nota 3") })
        TextField(value = nota4, onValueChange = { nota4 = it }, label = { Text("Nota 4") })
        Button(onClick = {
            val promedio = calcularPromedio(nota1.toDouble(), nota2.toDouble(), nota3.toDouble(), nota4.toDouble())
            resultado = "El promedio es: $promedio"
            mostrarModal = true
        }) {
            Text("Calcular promedio")
        }
        Button(onClick = onVolver) {
            Text("Volver")
        }

        if (mostrarModal) {
            AlertDialog(
                onDismissRequest = { mostrarModal = false },
                title = { Text(text = "Resultado") },
                text = { Text(text = resultado) },
                confirmButton = {
                    TextButton(onClick = { mostrarModal = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
fun Ejercicio3Screen(onVolver: () -> Unit) {
    var añoNacimiento by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var mostrarModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "DNI")
        TextField(value = añoNacimiento, onValueChange = { añoNacimiento = it }, label = { Text("Año de nacimiento") })
        Button(onClick = {
            val debeSacarDNI = debeSacarDNI(añoNacimiento.toInt())
            resultado = if (debeSacarDNI) "La persona es mayor de edad y puede sacar DNI" else "La persona aún no es mayor de edad"
            mostrarModal = true
        }) {
            Text("Verificar DNI")
        }
        Button(onClick = onVolver) {
            Text("Volver")
        }

        if (mostrarModal) {
            AlertDialog(
                onDismissRequest = { mostrarModal = false },
                title = { Text(text = "Resultado") },
                text = { Text(text = resultado) },
                confirmButton = {
                    TextButton(onClick = { mostrarModal = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
fun Ejercicio4Screen(onVolver: () -> Unit) {
    var resultado by remember { mutableStateOf("") }
    var mostrarModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Números pares")
        Button(onClick = {
            resultado = "Los números pares del 20 al 10 son: ${mostrarPares()}"
            mostrarModal = true
        }) {
            Text("Mostrar pares")
        }
        Button(onClick = onVolver) {
            Text("Volver")
        }

        if (mostrarModal) {
            AlertDialog(
                onDismissRequest = { mostrarModal = false },
                title = { Text(text = "Resultado") },
                text = { Text(text = resultado) },
                confirmButton = {
                    TextButton(onClick = { mostrarModal = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

// funciones
fun calcularPago(horas: Int, minutos: Int): Double {
    val tarifa = 25.0 //  25$ por minuto
    val tiempo = horas * 60 + minutos // Convertir a min
    val costo = tiempo * tarifa // calculo
    return costo
}

fun calcularPromedio(nota1: Double, nota2: Double, nota3: Double, nota4: Double): Double {
    val notas = arrayOf(nota1, nota2, nota3, nota4)
    notas.sort()
    return notas[1] * 0.2 + notas[2] * 0.3 + notas[3] * 0.5 // ignora la nota más baja
}

fun debeSacarDNI(añoNacimiento: Int): Boolean {
    val añoActual = Calendar.getInstance().get(Calendar.YEAR)
    val edad = añoActual - añoNacimiento
    return edad >= 18 // persona mayor a 18 saca dni
}

fun mostrarPares(): String {
    var pares = ""
    for (i in 20 downTo 10) {
        if (i % 2 == 0) {
            pares += "$i "
        }
    }
    return pares.trim()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}





