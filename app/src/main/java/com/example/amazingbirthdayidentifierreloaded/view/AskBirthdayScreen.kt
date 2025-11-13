package com.example.amazingbirthdayidentifierreloaded.view

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amazingbirthdayidentifierreloaded.ui.theme.AmazingBirthdayIdentifierReloadedTheme

/**
 * The initial screen that prompts the user to select their birthday.
 * This screen adapts its layout for portrait and landscape orientations.
 */
@Composable
fun AskBirthdayScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        // Use BoxWithConstraints to get the available screen dimensions.
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Check if the width is greater than the height to detect a landscape-like orientation.
            if (maxWidth > maxHeight) {
                // Use a Row for wide screens (landscape).
                AskLandscapeLayout()
            } else {
                // Use a Column for tall screens (portrait).
                AskPortraitLayout()
            }
        }
    }
}

/**
 * The layout for portrait orientation.
 */
@Composable
private fun AskPortraitLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SharedAskContent(isLandscape = false)
    }
}

/**
 * The layout for landscape orientation.
 */
@Composable
private fun AskLandscapeLayout() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SharedAskContent(isLandscape = true)
    }
}

/**
 * Contains the shared UI elements (Image and Text) for the initial screen.
 *
 * @param isLandscape Determines the arrangement and styling of child elements.
 */
@Composable
private fun SharedAskContent(isLandscape: Boolean) {
    Image(
        modifier = Modifier.size(if (isLandscape) 150.dp else 200.dp),
        imageVector = Icons.Default.QuestionMark,
        contentDescription = "Question mark illustration",
        alignment = Alignment.Center,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )

    if (isLandscape) {
        Spacer(modifier = Modifier.width(24.dp))
    }

    Column(
        horizontalAlignment = if (isLandscape) Alignment.Start else Alignment.CenterHorizontally
    ) {
        Text(
            text = "When is your birthday?",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            modifier = if (!isLandscape) Modifier.padding(top = 24.dp) else Modifier
        )

        Text(
            text = "Select a date to find out if it's today!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


/**
 * Previews for visualizing the AskBirthdayScreen in both orientations.
 */
@Preview(name = "Portrait Preview", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun AskBirthdayScreenPortraitPreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        AskBirthdayScreen()
    }
}

@Preview(name = "Landscape Preview", showBackground = true, widthDp = 720, heightDp = 360)
@Composable
fun AskBirthdayScreenLandscapePreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        AskBirthdayScreen()
    }
}