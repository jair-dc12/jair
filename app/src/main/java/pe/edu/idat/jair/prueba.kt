package pe.edu.idat.jair

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.idat.jair.ui.theme.JairTheme

import kotlin.math.ceil
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Calendar
import kotlin.math.ceil




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JairTheme {

    }
}

@Composable
fun MiColumna(){
    Column(modifier=Modifier.fillMaxSize())
    {
        Text(text = "texto1",modifier=Modifier.fillMaxWidth())
    }
}

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

    var pares=""
    for (i in 20 downTo 10) {
        if (i % 2 == 0) {
            pares += "$i"
        }
    }
    return pares.trim()
}



@Composable
fun MyApp() {
    var horas by remember { mutableStateOf("") }
    var minutos by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var nota4 by remember { mutableStateOf("") }
    var añoNacimiento by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var mostrarModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Ejercicio 1
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


        // Ejercicio 2
        Text(text = "Promedio de notas")
        TextField(value = nota1, onValueChange = { nota1 = it }, label = { Text("Nota 1") })
        TextField(value = nota2, onValueChange = { nota2 = it }, label = { Text("Nota 2") })
        TextField(value = nota3, onValueChange = { nota3 = it }, label = { Text("Nota 3") })
        TextField(value = nota4, onValueChange = { nota4 = it }, label = { Text("Nota 4") })
        Button(onClick = {
            val promedio = calcularPromedio(nota1.toDouble(), nota2.toDouble(), nota3.toDouble(), nota4.toDouble())
            resultado = "El promedio es : $promedio"
            mostrarModal=true


        }) {
            Text("Calcular promedio")
        }

        // Ejercicio 3
        Text(text = "DNI")
        TextField(value = añoNacimiento, onValueChange = { añoNacimiento = it }, label = { Text("Año de nacimiento") })
        Button(onClick = {
            val debeSacarDNI = debeSacarDNI(añoNacimiento.toInt())
            resultado= if (debeSacarDNI) "La persona es mayor de edad y puede sacar DNI " else "La persona aun no es mayor de edad"
            mostrarModal= true
        }) {
            Text("Verificar DNI")
        }

        // Ejercicio 4
        Button(onClick = {
            resultado = "Los números pares del 20 al 10 son: ${mostrarPares()}"
            mostrarModal = true
        }) {
            Text("Mostrar pares")
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













