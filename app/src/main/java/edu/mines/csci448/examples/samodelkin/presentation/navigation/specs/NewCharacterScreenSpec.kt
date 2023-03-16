package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinDetailScreen
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator
import edu.mines.csci448.examples.samodelkin.util.NetworkConnectionUtil
import edu.mines.csci448.examples.samodelkin.util.api.SamodelkinFetchr
import kotlinx.coroutines.CoroutineScope
import java.util.*

object NewCharacterScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewCharacterScreenSpec"

    override val route = "newCharacter"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?): String = route
    override val title = R.string.app_name

    @Composable
    override fun Content(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {

        val characterState = remember {
            mutableStateOf( CharacterGenerator.generateRandomCharacter(context) )
        }
        val samodelkinFetchr = remember { SamodelkinFetchr() }
        val apiCharacterState = samodelkinFetchr.characterState
            .collectAsState(context = coroutineScope.coroutineContext)

        LaunchedEffect(key1 = apiCharacterState.value) {
            val apiCharacter = apiCharacterState.value
            Log.d(LOG_TAG, "$apiCharacter")
            if (apiCharacter != null) {
                Log.d(LOG_TAG, "apiCharacter is not null")
                characterState.value = apiCharacter
            } else {
                Log.d(LOG_TAG, "apiCharacter is null")
                characterState.value = CharacterGenerator.generateRandomCharacter(context)
            }
        }

        NewCharacterScreen(
                character = characterState.value,
                apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
                onGenerateRandomCharacter = { characterState.value = CharacterGenerator.generateRandomCharacter(context) },
                onSaveCharacter = {
                    samodelkinViewModel.addCharacter(characterState.value)
                    navController.navigate(route = ListScreenSpec.buildRoute(ListScreenSpec.route))
                },
                onRequestApiCharacter = { samodelkinFetchr.getCharacter() }
            )
    }

    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {

    }
}