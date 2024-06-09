package pe.edu.idat.jair

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.idat.jair.ui.theme.JairTheme

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















