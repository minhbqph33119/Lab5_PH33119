package com.example.lab5_ph33119

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview(showBackground = true)
@Composable
fun Bai1(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Login()
        }
    }
}

@Composable
fun Login(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var messageDialog by remember { mutableStateOf("") }

    if (showDialog){
        Dialogg(
            onConfirmation = {showDialog = false},
            dialogTitle = "Notification",
            dialogMessage = messageDialog
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
        OutlinedTextField(value = username, onValueChange = {username = it}, label = { Text(text = "Username")})
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = {password = it}, label = { Text(text = "Password")})
        Spacer(modifier = Modifier.height(8.dp))
        RememberMeSwitch()
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            onClick = {
                if (username.isBlank() || password.isBlank()){
                    messageDialog = "Please enter username and password"
                } else {
                    messageDialog = "Login successful"
                }
                showDialog = true
            }) {
            Text(text = "Login")
        }
    }

}

@Composable
fun Dialogg(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogMessage: String,
){
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(20.dp).fillMaxWidth(1f),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(dialogTitle, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))
                Text(dialogMessage, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onConfirmation,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                    ) {
                    Text(text = "okay")
                }
            }
        }
    }
}

@Composable
fun RememberMeSwitch(){
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(checked = isChecked, onCheckedChange = {isChecked = it})
        Text(text = "Remember me", modifier = Modifier.padding(start = 10.dp))
    }
}

