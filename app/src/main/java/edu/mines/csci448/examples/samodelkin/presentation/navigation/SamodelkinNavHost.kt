package edu.mines.csci448.examples.samodelkin.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SamodelkinNavHost(modifier: Modifier = Modifier,
                      navController: NavHostController,
                      samodelkinViewModel: ISamodelkinViewModel,
                      context: Context,
                      coroutineScope: CoroutineScope
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if(screen != null) {
                    composable(
                        route = screen.route, 
                        arguments = screen.arguments
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            samodelkinViewModel = samodelkinViewModel,
                            context = context,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}