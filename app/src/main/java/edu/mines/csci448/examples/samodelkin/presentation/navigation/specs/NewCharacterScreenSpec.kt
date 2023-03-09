package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinDetailScreen
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator
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
        context: Context) {

        val characterState = remember {
            mutableStateOf( CharacterGenerator.generateRandomCharacter(context) )
        }

            NewCharacterScreen(
                character = characterState.value,
                onGenerateRandomCharacter = { characterState.value = CharacterGenerator.generateRandomCharacter(context) },
                onSaveCharacter = {
                    samodelkinViewModel.addCharacter(characterState.value)
                    navController.navigate(route = ListScreenSpec.buildRoute(ListScreenSpec.route))
                }
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