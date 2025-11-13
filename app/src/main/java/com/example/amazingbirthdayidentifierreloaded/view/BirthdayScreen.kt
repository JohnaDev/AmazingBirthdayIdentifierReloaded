package com.example.amazingbirthdayidentifierreloaded.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amazingbirthdayidentifierreloaded.R
import com.example.amazingbirthdayidentifierreloaded.ui.theme.AmazingBirthdayIdentifierReloadedTheme
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

/**
 * A composable screen that displays a celebratory "Happy Birthday" message
 * with a festive confetti animation. It adapts to both portrait and landscape orientations.
 */
@Composable
fun BirthdayScreen() {
    val party = remember {
        Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        // Use a Box to layer the confetti over the adaptive layout
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // BoxWithConstraints will determine the orientation
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (maxWidth > maxHeight) {
                    LandscapeBirthdayLayout()
                } else {
                    PortraitBirthdayLayout()
                }
            }

            // The KonfettiView is at the top level of the Box to cover the whole screen
            KonfettiView(
                parties = listOf(party),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/**
 * The layout for portrait orientation (or taller screens).
 */
@Composable
private fun PortraitBirthdayLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SharedBirthdayContent(isLandscape = false)
    }
}

/**
 * The layout for landscape orientation (or wider screens).
 */
@Composable
private fun LandscapeBirthdayLayout() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SharedBirthdayContent(isLandscape = true)
    }
}

/**
 * Contains the shared UI elements (Image and Texts) for the birthday screen.
 *
 * @param isLandscape Determines the arrangement and styling of child elements.
 */
@Composable
private fun SharedBirthdayContent(isLandscape: Boolean) {
    Image(
        modifier = Modifier.size(if (isLandscape) 150.dp else 200.dp),
        painter = painterResource(R.drawable.cake),
        contentDescription = "Birthday cake illustration",
        alignment = Alignment.Center
    )

    if (isLandscape) {
        Spacer(modifier = Modifier.width(24.dp))
    }

    Column(
        horizontalAlignment = if (isLandscape) Alignment.Start else Alignment.CenterHorizontally
    ) {
        Text(
            text = "Happy Birthday!",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            modifier = if (!isLandscape) Modifier.padding(top = 24.dp) else Modifier
        )

        Text(
            text = "Wishing you a wonderful day.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * Previews for visualizing the BirthdayScreen in both portrait and landscape.
 */
@Preview(name = "Portrait Preview", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun BirthdayScreenPortraitPreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        BirthdayScreen()
    }
}

@Preview(name = "Landscape Preview", showBackground = true, widthDp = 720, heightDp = 360)
@Composable
fun BirthdayScreenLandscapePreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        BirthdayScreen()
    }
}