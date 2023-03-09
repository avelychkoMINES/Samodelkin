package edu.mines.csci448.examples.samodelkin.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel

@Composable
fun SamodelkinTopBar(navController: NavHostController, samodelkinViewModel: ISamodelkinViewModel, context: Context) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    if (navBackStackEntryState.value != null) {
        IScreenSpec.TopBar(samodelkinViewModel, navController,
            navBackStackEntryState.value!!, context)
    }
}