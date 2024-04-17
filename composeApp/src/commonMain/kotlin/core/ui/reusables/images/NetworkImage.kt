package core.ui.reusables.images

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.util.DebugLogger
import core.ui.reusables.loading.shimmerBrush
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.ic_marvel_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    showShimmer: Boolean = true,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    url: String,
) {
    val context = LocalPlatformContext.current
    var loading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = url,
            contentDescription = null,
            contentScale = contentScale,
            onLoading = { loading = true },
            onSuccess = { loading = false },
            onError = { loading = false },
            colorFilter = colorFilter,
            error = painterResource(Res.drawable.ic_marvel_logo),
            imageLoader = ImageLoader.Builder(context).logger(DebugLogger()).build()
        )
        val shimmerBrush = shimmerBrush()
        AnimatedVisibility(
            visible = loading && showShimmer,
            label = "",
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = shimmerBrush)
            )
        }
    }
}