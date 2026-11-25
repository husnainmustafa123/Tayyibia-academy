package com.tayyibia.academy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AcademyApp() }
    }
}

@Composable
fun AcademyApp() {
    var email by remember { mutableStateOf("bsft3001@gmail.com") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("مرکزی منتظم اپنے اکاؤنٹ سے لاگ اِن کریں") }

    MaterialTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("دارالعلوم طیبیہ طاہریہ اکیڈمی", fontSize = 25.sp)
                Spacer(Modifier.height(8.dp))
                Text("DAR UL ULOOM TAYYIBIA TAHIRIA ACADEMY", fontSize = 12.sp)
                Spacer(Modifier.height(28.dp))
                Text("مرکزی منتظم کا لاگ اِن", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("ای میل") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = password, onValueChange = { password = it },
                    label = { Text("پاس ورڈ") }, singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(18.dp))
                Button(onClick = {
                    message = "یہ ابتدائی اسکرین ہے۔ Supabase کی تفصیلات شامل ہونے کے بعد اصل لاگ اِن فعال ہوگا۔"
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("لاگ اِن")
                }
                Spacer(Modifier.height(12.dp))
                Text(message, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(28.dp))
                Text("انتظامی شعبے", style = MaterialTheme.typography.titleMedium)
                listOf("طلبہ کا ریکارڈ", "اساتذہ کا ریکارڈ", "طلبہ کی حاضری",
                    "اساتذہ کی حاضری", "فیس اور رسیدیں", "سبق، سبقی اور منزل",
                    "امتحانات اور نتائج", "رپورٹس", "صارفین اور اجازتیں").forEach {
                    ElevatedCard(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Text(it, Modifier.fillMaxWidth().padding(16.dp))
                    }
                }
            }
        }
    }
}
