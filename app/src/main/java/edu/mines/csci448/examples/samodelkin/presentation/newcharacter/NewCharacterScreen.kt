package edu.mines.csci448.examples.samodelkin.presentation.newcharacter

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinCharacterDetails
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

@Composable
fun NewCharacterScreen(character: SamodelkinCharacter,
                       onGenerateRandomCharacter: () -> Unit,
                       onSaveCharacter: (SamodelkinCharacter) -> Unit) {
    when(LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(0.7f)
                ) {
                    SamodelkinCharacterDetails(character = character)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(0.3f)
                ) {
                    NewCharacterButton(
                        text = stringResource(R.string.generate_new_random_label),
                        onClick = onGenerateRandomCharacter
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NewCharacterButton(
                        text = stringResource(R.string.api_label),
                        enabled = false,
                        onClick = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NewCharacterButton(
                        text = stringResource(R.string.save_to_codex_label),
                        onClick = { onSaveCharacter( character ) }
                    )
                }
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Card {
                    SamodelkinCharacterDetails(character = character)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Box(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        NewCharacterButton(
                            text = stringResource(R.string.generate_new_random_label),
                            onClick = onGenerateRandomCharacter
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        NewCharacterButton(
                            text = stringResource(R.string.api_label),
                            enabled = false,
                            onClick = { }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                NewCharacterButton(
                    text = stringResource(R.string.save_to_codex_label),
                    onClick = { onSaveCharacter( character ) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewCharacterScreen() {
    val characterState = remember { mutableStateOf(CharacterGenerator.generateRandomCharacter()) }
    NewCharacterScreen(
        character = characterState.value,
        onGenerateRandomCharacter = { characterState.value = CharacterGenerator.generateRandomCharacter() },
        onSaveCharacter = { }
    )
}

@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun PreviewNewCharacterScreenLandscape() {
    val characterState = remember { mutableStateOf(CharacterGenerator.generateRandomCharacter()) }
    NewCharacterScreen(
        character = characterState.value,
        onGenerateRandomCharacter = { characterState.value = CharacterGenerator.generateRandomCharacter() },
        onSaveCharacter = { }
    )
}