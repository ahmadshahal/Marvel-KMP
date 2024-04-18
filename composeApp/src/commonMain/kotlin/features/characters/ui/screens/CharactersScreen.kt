package features.characters.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.ui.reusables.buttons.BackToTopButton
import enableLightEdgeToEdge
import features.characters.domain.entities.Character
import features.characters.ui.components.CharactersLazyGrid
import features.characters.ui.viewmodels.CharactersViewModel
import kotlinx.coroutines.launch
import marvel.composeapp.generated.resources.Res
import marvel.composeapp.generated.resources.ic_hamburger
import marvel.composeapp.generated.resources.ic_marvel_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


object CharactersScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        enableLightEdgeToEdge()
        val viewModel = getScreenModel<CharactersViewModel>()
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val coroutineScope = rememberCoroutineScope()
        val lazyGridState = rememberLazyGridState()
        val showScrollToTopButton by remember {
            derivedStateOf { lazyGridState.firstVisibleItemIndex > 2 }
        }
        val navController = LocalNavigator.currentOrThrow
        val characterPagingItems: LazyPagingItems<Character> =
            viewModel.charactersState.collectAsLazyPagingItems()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = { CharactersTopBar(scrollBehavior) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
                    )
            ) {
                CharactersLazyGrid(
                    characterPagingItems = characterPagingItems,
                    onClickCharacter = {
                        navController.push(CharacterDetailsScreen(it))
                    },
                    lazyGridState = lazyGridState
                )
                AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 24.dp),
                    visible = showScrollToTopButton,
                    enter = slideInVertically { -it * 2 },
                    exit = slideOutVertically { -it * 2 },
                ) {
                    BackToTopButton(
                        onClick = {
                            coroutineScope.launch {
                                lazyGridState.animateScrollToItem(0)
                            }
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    private fun CharactersTopBar(scrollBehavior: TopAppBarScrollBehavior) {
        CenterAlignedTopAppBar(
            title = {
                Image(
                    painter = painterResource(Res.drawable.ic_marvel_logo),
                    contentDescription = null,
                )
            },
            modifier = Modifier,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_hamburger),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}