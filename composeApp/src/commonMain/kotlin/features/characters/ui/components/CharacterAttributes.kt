package features.characters.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import features.characters.domain.entities.Character
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.ic_age
import marvel.composeapp.generated.resources.ic_height
import marvel.composeapp.generated.resources.ic_location
import marvel.composeapp.generated.resources.ic_weight
import marvel.composeapp.generated.resources.kg
import marvel.composeapp.generated.resources.m
import marvel.composeapp.generated.resources.years
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CharacterAttributes(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CharacterAttribute(
            painter = painterResource(Res.drawable.ic_age),
            value = character.age + " " + stringResource(Res.string.years)
        )
        CharacterAttribute(
            painter = painterResource(Res.drawable.ic_weight),
            value = character.weight + " " + stringResource(Res.string.kg)
        )
        CharacterAttribute(
            painter = painterResource(Res.drawable.ic_height),
            value = character.height + stringResource(Res.string.m)
        )
        CharacterAttribute(
            painter = painterResource(Res.drawable.ic_location),
            value = character.location
        )
    }
}

@Composable
private fun CharacterAttribute(
    modifier: Modifier = Modifier,
    painter: Painter,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 12.sp
        )
    }
}