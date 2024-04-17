package core.ui.reusables.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.ui.reusables.buttons.DefaultButton
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.ic_marvel_logo
import marvel.composeapp.generated.resources.retry
import marvel.composeapp.generated.resources.your_internet_connection_might_be_having_some_issues
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NetflixErrorBox(
    modifier: Modifier = Modifier,
    message: String = stringResource(Res.string.your_internet_connection_might_be_having_some_issues),
    onClickTryAgain: () -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(110.dp),
            painter = painterResource(Res.drawable.ic_marvel_logo),
            contentDescription = null
        )
        Text(
            text = message,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(32.dp))
        DefaultButton(onClick = onClickTryAgain) {
            Text(text = stringResource(Res.string.retry))
        }
    }
}