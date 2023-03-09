package edu.mines.csci448.examples.samodelkin.presentation.detail

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

private const val LOG_TAG = "448.SamodelkinCharacterDetails"

@Composable
fun SamodelkinCharacterDetails(character: SamodelkinCharacter) {
    val imageSize = with(LocalDensity.current) { 384.toDp() }

    when (val orientation = LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.65f)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .width(imageSize)
                                .height(imageSize)
                                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
                        ) {
                            Log.d(LOG_TAG, "Displaying avatar \"${character.avatarAssetPath}\"")
                            AvatarImage(
                                imageSize = imageSize,
                                assetPath = character.avatarAssetPath
                            )
                        }
                        Column {
                            NameSection(character.name)
                            Spacer(modifier = Modifier.height(16.dp))
                            RaceSection(character.race)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfessionSection(character.profession)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier.weight(0.35f)
                ) {
                    StatsSection(
                        orientation = orientation,
                        dexterity = character.dexterity,
                        strength = character.strength,
                        wisdom = character.wisdom,
                        intelligence = character.intelligence,
                        charisma = character.charisma,
                        constitution = character.constitution
                    )
                }
            }
        }
        else -> {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .width(imageSize)
                            .height(imageSize)
                            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
                    ) {
                        Log.d(LOG_TAG, "Displaying avatar \"${character.avatarAssetPath}\"")
                        AvatarImage(
                            imageSize = imageSize,
                            assetPath = character.avatarAssetPath
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        NameSection(character.name)
                        Spacer(modifier = Modifier.height(16.dp))
                        RaceSection(character.race)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                ProfessionSection(character.profession)
                Spacer(modifier = Modifier.height(16.dp))
                StatsSection(
                    orientation = orientation,
                    dexterity = character.dexterity,
                    strength = character.strength,
                    wisdom = character.wisdom,
                    intelligence = character.intelligence,
                    charisma = character.charisma,
                    constitution = character.constitution
                )
            }
        }
    }
}

@Composable
private fun NameSection(characterName: String) {
    Column {
        SectionHeader(stringResource(R.string.name_label))
        Text(characterName)
    }
}

@Composable
private fun RaceSection(characterRace: String) {
    Column {
        SectionHeader(stringResource(R.string.race_label))
        Text(characterRace)
    }
}

@Composable
private fun ProfessionSection(characterProfession: String) {
    Column {
        SectionHeader(stringResource(R.string.profession_label))
        Text(characterProfession)
    }
}

@Composable
private fun StatsSection(orientation: Int, dexterity: Int, strength: Int, wisdom: Int, intelligence: Int, charisma: Int, constitution: Int) {
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Column {
                SectionHeader(stringResource(R.string.stats_label))
                StatsDisplay(stringResource(R.string.dexterity_label), dexterity)
                StatsDisplay(stringResource(R.string.strength_label), strength)
                StatsDisplay(stringResource(R.string.wisdom_label), wisdom)
                StatsDisplay(stringResource(R.string.intelligence_label), intelligence)
                StatsDisplay(stringResource(R.string.charisma_label), charisma)
                StatsDisplay(stringResource(R.string.constitution_label), constitution)
            }
        }
        else -> {
            Column {
                SectionHeader(stringResource(R.string.stats_label))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        StatsDisplay(stringResource(R.string.dexterity_label), dexterity)
                        StatsDisplay(stringResource(R.string.strength_label), strength)
                        StatsDisplay(stringResource(R.string.wisdom_label), wisdom)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        StatsDisplay(stringResource(R.string.intelligence_label), intelligence)
                        StatsDisplay(stringResource(R.string.charisma_label), charisma)
                        StatsDisplay(stringResource(R.string.constitution_label), constitution)
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(headerText: String) {
    Column {
        Text(
            text = headerText,
            fontSize = 24.sp
        )
        Divider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp
        )
    }
}

@Composable
private fun StatsDisplay(statName: String, statValue: Int) {
    Row{
        Text(
            text = statName,
            modifier = Modifier.weight(0.7f)
        )
        Text(
            text = statValue.toString(),
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun AvatarImage(imageSize: Dp, assetPath: String?) {
    if(assetPath != null) {
        AsyncImage(
            modifier = Modifier
                .width(imageSize)
                .height(imageSize)
                .padding(4.dp),
            model = assetPath,
            contentDescription = "Avatar"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSamodelkinCharacterDetails() {
    val context = LocalContext.current
    val character = CharacterGenerator.generateRandomCharacter(context)
    SamodelkinCharacterDetails(character = character)
}

@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun PreviewSamodelkinCharacterDetailsLandscape() {
    val context = LocalContext.current
    val character = CharacterGenerator.generateRandomCharacter(context)
    SamodelkinCharacterDetails(character = character)
}