package com.example.appmob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Composable
fun AppScreen() {

    var mode by remember { mutableStateOf("50-50") }

    var m1 by remember { mutableStateOf("") }
    var m2 by remember { mutableStateOf("") }
    var f1 by remember { mutableStateOf("") }
    var f2 by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(20.dp)) {

        Text("محاسبه نمره", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(20.dp))

        Row {
            RadioButton(
                selected = mode == "50-50",
                onClick = { mode = "50-50" }
            )
            Text("50-50")

            Spacer(Modifier.width(20.dp))

            RadioButton(
                selected = mode == "30-40-30",
                onClick = { mode = "30-40-30" }
            )
            Text("30-40-30")
        }

        Spacer(Modifier.height(15.dp))

        OutlinedTextField(
            value = m1,
            onValueChange = { m1 = it },
            label = { Text("مستمر اول") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = m2,
            onValueChange = { m2 = it },
            label = { Text("مستمر دوم") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = f1,
            onValueChange = { f1 = it },
            label = { Text("پایانی اول") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = f2,
            onValueChange = { f2 = it },
            label = { Text("پایانی دوم") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(20.dp))

        Button(onClick = {

            val a = m1.toDoubleOrNull()
            val b = m2.toDoubleOrNull()
            val c = f1.toDoubleOrNull()
            val d = f2.toDoubleOrNull()

            if (a == null || b == null || c == null || d == null || a < 0 || b < 0 || c < 0 || d < 0) {
                result = "مقادیر وارد شده معتبر نیست"
                return@Button
            }

            val res = if (mode == "50-50") {
                (8 / ((a + b) + 2 * c)) + d
            } else {
                ((a + b) * 0.15) + (c * 0.4) + (d * 0.3)
            }

            result = "نمره نهایی: %.2f".format(res)

        }) {
            Text("محاسبه")
        }

        Spacer(Modifier.height(20.dp))

        Text(result)

        Spacer(Modifier.height(30.dp))

        Text(
            "برنامه نویسان: دکتر عبدالغفار مطوف و جابر عامری / گروه ریاضی استان خوزستان"
        )
    }
}
