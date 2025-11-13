package com.example.amazingbirthdayidentifierreloaded.model

/**
 * Represents the current state of the birthday check screen.
 *
 * This data class holds all the necessary information to render the UI. It is designed to be
 * immutable, meaning that to change the state, a new instance of this class should be created
 * with the updated values. This pattern works seamlessly with Jetpack Compose and StateFlow.
 *
 * @property isBirthDay The current status of the birthday verification, represented by the [Type] enum.
 *                      It defaults to [Type.ASK], which is the initial state before the user
 *                      has selected a date.
 */
data class BirthdayState(val isBirthDay: Type = Type.ASK)