package org.example.firstcmpproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import firstcmpproject.composeapp.generated.resources.Res
import firstcmpproject.composeapp.generated.resources.netflix_logo_app_bar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetflixLoginScreen() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isPasswordShown by mutableStateOf(false)

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            NetflixLoginScreenAppbar()
        }) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 48.dp)
            ) {
                //Email or phone number
                OutlinedTextField(
                    value = email,
                    onValueChange = { text ->
                        email = text

                    },
                    placeholder = {
                        Text("Email or phone number", color = Color(red = 145, green = 145, blue = 145))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(red = 51, green = 51, blue = 51),
                        unfocusedContainerColor = Color(red = 51, green = 51, blue = 51),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White

                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                //Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { text ->
                        password = text

                    },
                    placeholder = {
                        Text("Password", color = Color(red = 145, green = 145, blue = 145))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(red = 51, green = 51, blue = 51),
                        unfocusedContainerColor = Color(red = 51, green = 51, blue = 51),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White

                    ),
                    visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
                    suffix = {
                        Text(
                            if (isPasswordShown) "HIDE" else "SHOW",
                            color = Color(red = 145, green = 145, blue = 145),
                            modifier = Modifier.clickable {
                                isPasswordShown = !isPasswordShown
                            }
                        )
                    },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                //Sign in button
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 83, green = 14, blue = 13),
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Sign in",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,

                        )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "OR",
                    color = Color(red = 145, green = 145, blue = 145),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp

                    )

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 51, green = 51, blue = 51),
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Use a sign-in code",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,

                        )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Forgot Passoword?",
                    color = Color(red = 181, green = 181, blue = 181),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {

                    }

                )
                Spacer(modifier = Modifier.height(32.dp))
                SignInTermsAndCondition()

            }
        }
    }
}

@Composable
fun SignInTermsAndCondition() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Color(145, 145, 145)),

            ) {
            append("Sign in protected by Google reCAPTCHA to ensure you're not a bot.")
        }


        withStyle(
            style = SpanStyle(
                color = Color(145, 145, 145),
                fontWeight = FontWeight.Bold
            ),
        ) {
            append("Learn More.")
        }

    }
    Text(annotatedString, textAlign = TextAlign.Center)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetflixLoginScreenAppbar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ),
        title = {
            Image(
                painterResource(Res.drawable.netflix_logo_app_bar),
                contentDescription = "Netflix Appbar Icon"
            )
        },
        navigationIcon = {
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "Back button",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        },
        actions = {
            Text(
                "Help",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    )
}


@Preview
@Composable
fun NetflixLoginScreenPreview() {
    NetflixLoginScreen()
}