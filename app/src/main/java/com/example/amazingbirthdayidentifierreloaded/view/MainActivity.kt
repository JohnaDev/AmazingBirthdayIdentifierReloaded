package com.example.amazingbirthdayidentifierreloaded.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amazingbirthdayidentifierreloaded.model.Type
import com.example.amazingbirthdayidentifierreloaded.viewmodel.BirthdayViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val birthdayViewModel: BirthdayViewModel = viewModel()
            val state = birthdayViewModel.state.collectAsState()
            var showDatePicker by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    when (state.value.isBirthDay) {
                        Type.ASK -> {
                            Text(text = "Enter your birth date")
                        }

                        Type.TRUE -> {
                            Text(text = "Happy birthday!")
                        }

                        Type.FALSE -> {
                            Text(text = "Sorry it's not your birthday. Try again tomorrow")
                        }
                    }
                    Button(onClick = { showDatePicker = true }) {
                        Text(
                            text = "Enter birthday"
                        )
                    }
                }
            }

            if (showDatePicker) {
                @Suppress("AssignedValueIsNeverRead")
                (DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val selectedDateMillis = datePickerState.selectedDateMillis
                                if (selectedDateMillis != null) {
                                    birthdayViewModel.checkIsBirthday(selectedDateMillis)
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                })
            }
        }
    }
}