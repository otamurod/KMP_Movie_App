package uz.otamurod.kmp.movieapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Dark4,
            secondary = Red,
            surface = Dark2,
            background = Dark1
        )
    } else {
        lightColorScheme(
            primary = Light4,
            secondary = Green,
            surface = Light2,
            background = Color.White.copy(alpha = 0.6f)
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
// Add new colors
val Dark1 = Color(red = 22, green = 23, blue = 29)
val Dark2 = Color(red = 28, green = 29, blue = 35)
val Dark3 = Color(red = 31, green = 31, blue = 36)
val Dark4 = Color(red = 34, green = 35, blue = 40)
val Red = Color(red = 220, green = 0, blue = 59)

val Light1 = Color(red = 33, green = 150, blue = 243, alpha = 255)
val Light2 = Color(red = 3, green = 155, blue = 229, alpha = 255)
val Light3 = Color(red = 129, green = 212, blue = 250, alpha = 255)
val Light4 = Color(red = 3, green = 169, blue = 244, alpha = 255)
val Green = Color(red = 14, green = 230, blue = 22, alpha = 255)
