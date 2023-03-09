package edu.mines.csci448.examples.samodelkin.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

@Composable
fun SamodelkinDetailScreen(character: SamodelkinCharacter) {
    SamodelkinCharacterDetails(character = character)
}

@Preview(showBackground = true)
@Composable
private fun PreviewSamodelkinDetailScreen() {
    val context = LocalContext.current
    val character = CharacterGenerator.generateRandomCharacter(context)
    SamodelkinDetailScreen(character = character)
}