package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinDetailScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import java.util.*
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

object DetailScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.DetailScreenSpec"

    private const val ROUTE_BASE = "detail"
    private const val ARG_UUID_NAME = "uuid"

    private fun buildFullRoute(argVal: String): String {
        var fullRoute = ROUTE_BASE
        if(argVal == ARG_UUID_NAME) {
            fullRoute += "/{$argVal}"
            Log.d(LOG_TAG, "Built base route $fullRoute")
        } else {
            fullRoute += "/$argVal"
            Log.d(LOG_TAG, "Built specific route $fullRoute")
        }
        return fullRoute
    }

    override val route = buildFullRoute(ARG_UUID_NAME)

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(ARG_UUID_NAME) {
            type = NavType.StringType
        }
    )

    override fun buildRoute(vararg args: String?): String = buildFullRoute(args[0] ?: "0")

    override val title = R.string.app_name

    @Composable
    override fun Content(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val uuid = navBackStackEntry.arguments?.getString(ARG_UUID_NAME)?.let { UUID.fromString(it) }
        if (uuid != null) {
            samodelkinViewModel.loadCharacterByUUID(uuid)
            val character = samodelkinViewModel.currentCharacterState.collectAsState().value
            if (character != null) {
                SamodelkinDetailScreen(character = character)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val character = samodelkinViewModel.currentCharacterState.collectAsState().value
        val openDialog = remember {
            mutableStateOf(false)
        }
        
        IconButton(onClick = {
            openDialog.value = true
        }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.menu_delete_character_desc)
            )
        }
        if (openDialog.value && character != null) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        samodelkinViewModel.deleteCharacter(character)
                        navController.navigate(route = ListScreenSpec.buildRoute(ListScreenSpec.route))
                    })
                    { Text(text = "OK") }
                },
                dismissButton = {
                    TextButton(onClick = { openDialog.value = false })
                    { Text(text = "Cancel") }
                },
                title = { Text(text = "Confirm Delete") },
                text = { 
                    Text(text = "Are you sure you wish to delete this character?\n\n" + character.name)
                },
            )
        }
    }
}