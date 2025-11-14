package com.example.amazingbirthdayidentifierreloaded.view

import NotYourBirthdayScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amazingbirthdayidentifierreloaded.model.Type
import com.example.amazingbirthdayidentifierreloaded.ui.theme.AmazingBirthdayIdentifierReloadedTheme
import com.example.amazingbirthdayidentifierreloaded.viewmodel.BirthdayViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmazingBirthdayIdentifierReloadedTheme {
                val birthdayViewModel: BirthdayViewModel = viewModel()
                val state = birthdayViewModel.state.collectAsState()
                var showDatePicker by remember { mutableStateOf(false) }
                val datePickerState = rememberDatePickerState()

                Scaffold(
                    // 2. Place the persistent button in the bottomBar slot
                    bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(25.dp, 25.dp, 25.dp, 45.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                modifier = Modifier.padding(30.dp),
                                onClick = { showDatePicker = true }) {
                                // 3. Change the button text based on the state
                                val buttonText = if (state.value.isBirthDay == Type.ASK) {
                                    "Select a Date"
                                } else {
                                    "Select Another Date"
                                }
                                Text(text = buttonText, fontSize = TextUnit(20f, TextUnitType.Sp))
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), // Apply the padding here
                        contentAlignment = Alignment.Center
                    ) {
                        when (state.value.isBirthDay) {
                            // 5. AskBirthdayScreen no longer needs its own button
                            Type.ASK -> AskBirthdayScreen()
                            Type.TRUE -> BirthdayScreen()
                            Type.FALSE -> NotYourBirthdayScreen()
                        }
                    }
                }

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    val selectedDateMillis = datePickerState.selectedDateMillis
                                    if (selectedDateMillis != null) {
                                        birthdayViewModel.checkIsBirthday(selectedDateMillis)
                                    }
                                    showDatePicker = false
                                }
                            ) { Text("OK") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        }
    }
}