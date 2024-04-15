package app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.gilroy_black
import marvel.composeapp.generated.resources.gilroy_bold
import marvel.composeapp.generated.resources.gilroy_extra_bold
import marvel.composeapp.generated.resources.gilroy_light
import marvel.composeapp.generated.resources.gilroy_medium
import marvel.composeapp.generated.resources.gilroy_regular
import marvel.composeapp.generated.resources.gilroy_semi_bold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@Composable
fun marvelTypography(): Typography {
    @OptIn(ExperimentalResourceApi::class)
    val gilroyFontFamily = FontFamily(
        Font(Res.font.gilroy_black, FontWeight.Black),
        Font(Res.font.gilroy_bold, FontWeight.Bold),
        Font(Res.font.gilroy_light, FontWeight.Light),
        Font(Res.font.gilroy_medium, FontWeight.Medium),
        Font(Res.font.gilroy_regular, FontWeight.Normal),
        Font(Res.font.gilroy_semi_bold, FontWeight.SemiBold),
        Font(Res.font.gilroy_extra_bold, FontWeight.ExtraBold),
    )

    val defaultTypography = Typography()

    return Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = gilroyFontFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = gilroyFontFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = gilroyFontFamily),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = gilroyFontFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = gilroyFontFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = gilroyFontFamily),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = gilroyFontFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = gilroyFontFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = gilroyFontFamily),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = gilroyFontFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = gilroyFontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = gilroyFontFamily),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = gilroyFontFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = gilroyFontFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = gilroyFontFamily)
    )
}
