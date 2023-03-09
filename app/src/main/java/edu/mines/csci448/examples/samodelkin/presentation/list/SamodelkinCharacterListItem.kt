package edu.mines.csci448.examples.samodelkin.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SamodelkinCharacterListItem(character: SamodelkinCharacter,
                                onSelectCharacter: (SamodelkinCharacter) -> Unit) {
    val fontSize = 14.sp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onSelectCharacter(character) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_name),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(2.dp),
                    text = character.name,
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Row(
                    modifier = Modifier.weight(0.4f)
                ) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = stringResource(id = R.string.label_race),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = fontSize
                    )
                    Text(
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(2.dp),
                        text = character.race,
                        fontSize = fontSize,
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    modifier = Modifier.weight(0.6f)
                ) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = stringResource(id = R.string.label_profession),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = fontSize
                    )
                    Text(
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(2.dp),
                        text = character.profession,
                        fontSize = fontSize,
                        textAlign = TextAlign.Start
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_dex_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.dexterity.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_wis_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.wisdom.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_str_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.strength.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_int_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.intelligence.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_cha_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.charisma.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = stringResource(id = R.string.label_con_short),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = fontSize
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(2.dp),
                    text = character.constitution.toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSamodelkinCharacterListItem() {
    val character = CharacterGenerator.generateRandomCharacter()
    SamodelkinCharacterListItem(character = character) {}
}