package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinDetailScreen
import edu.mines.csci448.examples.samodelkin.presentation.list.SamodelkinListScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import java.util.*
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel

object ListScreenSpec : IScreenSpec {

    override val route = "list"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route
    override val title = R.string.app_name

    @Composable
    override fun Content(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val characterListState =
            samodelkinViewModel.characterListState.collectAsState()


        if (characterListState.value.isEmpty()) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(modifier = Modifier
                    .padding(8.dp)) {
                    Text(text = "There are currently no characters in your codex")
                    Button(onClick = { navController.navigate(route = NewCharacterScreenSpec.buildRoute(
                        NewCharacterScreenSpec.route)) }) {
                        Text(text = "Add New Character")
                    }
                }
            }
        }
        else {
            SamodelkinListScreen(characterList = characterListState.value) {
                    character ->
                navController.navigate(route = DetailScreenSpec.buildRoute(character.id.toString()))
            }
        }
    }

    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        IconButton(onClick = { navController.navigate(route = NewCharacterScreenSpec.buildRoute(NewCharacterScreenSpec.route)) }) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = stringResource(R.string.menu_add_character_desc)
            )
        }
    }
}