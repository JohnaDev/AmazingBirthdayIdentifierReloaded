import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amazingbirthdayidentifierreloaded.R
import com.example.amazingbirthdayidentifierreloaded.ui.theme.AmazingBirthdayIdentifierReloadedTheme

/**
 * A composable screen that informs the user it is not their birthday.
 * It is designed to be clean, informative, and consistent with the app's overall theme,
 * and it adapts its layout for both portrait and landscape orientations.
 */
@Composable
fun NotYourBirthdayScreen() {
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
                LandscapeLayout()
            } else {
                // Use a Column for tall screens (portrait).
                PortraitLayout()
            }
        }
    }
}

/**
 * The layout for portrait orientation (or taller screens).
 */
@Composable
private fun PortraitLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SharedContent(isLandscape = false)
    }
}

/**
 * The layout for landscape orientation (or wider screens).
 */
@Composable
private fun LandscapeLayout() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SharedContent(isLandscape = true)
    }
}

/**
 * Contains the shared UI elements (Image and Texts) used by both layouts.
 *
 * @param isLandscape Determines the arrangement of child elements.
 */
@Composable
private fun SharedContent(isLandscape: Boolean) {
    Image(
        // In landscape, we might want a slightly smaller image.
        modifier = Modifier.size(if (isLandscape) 150.dp else 200.dp),
        painter = painterResource(R.drawable.sad),
        contentDescription = "Sad face icon",
        alignment = Alignment.Center
    )

    // Add a spacer between the image and text only in landscape mode.
    if (isLandscape) {
        Spacer(modifier = Modifier.width(24.dp))
    }

    Column(
        // In landscape, align text to the start; otherwise, center it.
        horizontalAlignment = if (isLandscape) Alignment.Start else Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry, not today!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            // In landscape, align text to the start; otherwise, center it.
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            // In portrait, add top padding to separate from the image above.
            modifier = if (!isLandscape) Modifier.padding(top = 24.dp) else Modifier
        )

        Text(
            text = "It looks like today isn't your birthday. Try again tomorrow.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = if (isLandscape) TextAlign.Start else TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


/**
 * A preview composable to visualize the NotYourBirthdayScreen in Android Studio.
 */
@Preview(name = "Portrait Preview", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun NotYourBirthdayScreenPortraitPreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        NotYourBirthdayScreen()
    }
}

@Preview(name = "Landscape Preview", showBackground = true, widthDp = 720, heightDp = 360)
@Composable
fun NotYourBirthdayScreenLandscapePreview() {
    AmazingBirthdayIdentifierReloadedTheme {
        NotYourBirthdayScreen()
    }
}