package features.characters.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.ui.reusables.error.NetflixErrorBox
import core.ui.reusables.images.NetworkImage
import core.ui.reusables.loading.NetflixLoadingBox
import core.ui.states.FetchState
import features.characters.domain.entities.Character
import features.characters.domain.entities.publications.Comic
import features.characters.domain.entities.publications.Event
import features.characters.domain.entities.publications.Serie
import features.characters.domain.entities.publications.Story
import features.characters.domain.enums.PublicationType
import features.characters.ui.components.CharacterAttributes
import features.characters.ui.components.PublicationsLazyRow
import features.characters.ui.viewmodels.CharacterDetailsViewModel
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.comics
import marvel.composeapp.generated.resources.events
import marvel.composeapp.generated.resources.hasn_t_participated_in_any_comics
import marvel.composeapp.generated.resources.hasn_t_participated_in_any_events
import marvel.composeapp.generated.resources.hasn_t_participated_in_any_series
import marvel.composeapp.generated.resources.hasn_t_participated_in_any_stories
import marvel.composeapp.generated.resources.ic_back
import marvel.composeapp.generated.resources.series
import marvel.composeapp.generated.resources.stories
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class CharacterDetailsScreen(private val characterId: Int) : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<CharacterDetailsViewModel>()
        LaunchedEffect(key1 = Unit) {
            viewModel.getCharacterDetails(characterId)
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Black,
            topBar = { CharacterDetailsTopBar() }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
                    ),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = viewModel.characterDetailsState.fetchState,
                    contentAlignment = Alignment.Center,
                    label = "",
                    transitionSpec = { fadeIn() togetherWith fadeOut() }
                ) {
                    when (val fetchState = it) {
                        is FetchState.Error<*> -> NetflixErrorBox(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            message = fetchState.message.asString(),
                            onClickTryAgain = {
                                viewModel.getCharacterDetails(characterId)
                            }
                        )
                        is FetchState.Success<*> -> {
                            CharacterBackground(thumbnail = viewModel.character.thumbnail)
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                CharacterDetails(
                                    modifier = Modifier.padding(horizontal = 24.dp),
                                    character = viewModel.character
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                PublicationsBox(
                                    publicationType = viewModel.characterDetailsState.selectedPublicationType,
                                    comics = viewModel.comics,
                                    series = viewModel.series,
                                    stories = viewModel.stories,
                                    events = viewModel.events,
                                    onPublicationTypeChange = viewModel::onPublicationTypeChange
                                )
                            }
                        }
                        else -> NetflixLoadingBox()
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun PublicationsBox(
        modifier: Modifier = Modifier,
        publicationType: PublicationType,
        comics: List<Comic>,
        series: List<Serie>,
        stories: List<Story>,
        events: List<Event>,
        onPublicationTypeChange: (PublicationType) -> Unit
    ) {
        AnimatedContent(
            modifier = modifier,
            targetState = publicationType,
            label = ""
        ) { productType ->
            when(productType) {
                PublicationType.COMICS -> PublicationsLazyRow(
                    publications = comics,
                    onProductTypeChange = {
                        onPublicationTypeChange(PublicationType.SERIES)
                    },
                    title = stringResource(Res.string.comics),
                    emptyStatement = stringResource(Res.string.hasn_t_participated_in_any_comics),
                    nextPublicationTitle = stringResource(Res.string.series)
                )
                PublicationType.SERIES -> PublicationsLazyRow(
                    publications = series,
                    onProductTypeChange = {
                        onPublicationTypeChange(PublicationType.STORIES)
                    },
                    title = stringResource(Res.string.series),
                    emptyStatement = stringResource(Res.string.hasn_t_participated_in_any_series),
                    nextPublicationTitle = stringResource(Res.string.stories)
                )
                PublicationType.STORIES -> PublicationsLazyRow(
                    publications = stories,
                    onProductTypeChange = {
                        onPublicationTypeChange(PublicationType.EVENTS)
                    },
                    title = stringResource(Res.string.stories),
                    emptyStatement = stringResource(Res.string.hasn_t_participated_in_any_stories),
                    nextPublicationTitle = stringResource(Res.string.events)
                )
                else -> PublicationsLazyRow(
                    publications = events,
                    onProductTypeChange = {
                        onPublicationTypeChange(PublicationType.COMICS)
                    },
                    title = stringResource(Res.string.events),
                    emptyStatement = stringResource(Res.string.hasn_t_participated_in_any_events),
                    nextPublicationTitle = stringResource(Res.string.comics)
                )
            }
        }
    }

    @Composable
    private fun BoxScope.CharacterBackground(thumbnail: String) {
        NetworkImage(
            url = thumbnail,
            modifier = Modifier.fillMaxSize(),
            showShimmer = false
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8F),
                            Color.Black
                        ),
                    )
                )
        )
    }

    @Composable
    private fun ColumnScope.CharacterDetails(modifier: Modifier = Modifier, character: Character) {
        Column(modifier = modifier) {
            Text(
                text = character.name,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 56.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            CharacterAttributes(modifier = Modifier.fillMaxWidth(), character = character)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    private fun CharacterDetailsTopBar() {
        val navController = LocalNavigator.currentOrThrow
        TopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(onClick = navController::pop) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            },
            title = { }
        )
    }
}