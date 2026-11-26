
package com.tayyibia.academy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF075B43)
private val Gold = Color(0xFFD6AE55)
private val Cream = Color(0xFFFFF9ED)
private val DarkGreen = Color(0xFF063D30)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TayyibiaApp()
        }
    }
}

@Composable
fun TayyibiaApp() {
    var email by remember {
        mutableStateOf("bsft3001@gmail.com")
    }
    var password by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Green,
            secondary = Gold,
            background = DarkGreen,
            surface = Color(0xFF104B3C)
        )
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = DarkGreen
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                        color = Gold,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "دارالعلوم",
                        color = Cream,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "طیبیہ طاہریہ اکیڈمی",
                        color = Gold,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "مدرسہ انتظامی نظام",
                        color = Cream,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.height(35.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Cream
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "مرکزی منتظم کا لاگ اِن",
                                color = Green,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "اپنے اکاؤنٹ کی تفصیلات درج کریں",
                                color = Color.DarkGray,
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            Text(
                                text = "ای میل ایڈریس",
                                color = Green,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold
                            )

                            CompositionLocalProvider(
                                LocalLayoutDirection provides LayoutDirection.Ltr
                            ) {
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = {
                                        email = it
                                        message = ""
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    placeholder = {
                                        Text("Email address")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "پاس ورڈ",
                                color = Green,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold
                            )

                            OutlinedTextField(
                                value = password,
                                onValueChange = {
                                    password = it
                                    message = ""
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                placeholder = {
                                    Text("Password")
                                },
                                visualTransformation =
                                    PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password
                                )
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            Button(
                                onClick = {
                                    message =
                                        if (email.isBlank() || password.isBlank()) {
                                            "براہ کرم ای میل اور پاس ورڈ درج کریں"
                                        } else {
                                            "لاگ اِن سسٹم ابھی Supabase سے منسلک نہیں ہوا۔"
                                        }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Green
                                )
                            ) {
                                Text(
                                    text = "لاگ اِن کریں",
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            if (message.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    text = message,
                                    color = Green,
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "اللہ تعالیٰ اس ادارے کو ترقی عطا فرمائے",
                        color = Gold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "© Dar ul Uloom Tayyibia Tahiria Academy",
                        color = Cream,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
