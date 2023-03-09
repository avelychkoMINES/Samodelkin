package edu.mines.csci448.examples.samodelkin.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.data.SamodelkinRepo
import edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.NewCharacterScreenSpec
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.PreviewSamodelkinViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SamodelkinListScreen(characterList: List<SamodelkinCharacter>,
                         onSelectCharacter: (SamodelkinCharacter) -> Unit) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(characterList) { character ->
                SamodelkinCharacterListItem(
                    character = character,
                    onSelectCharacter = onSelectCharacter
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSamodelkinListScreen() {
    val context = LocalContext.current
    SamodelkinListScreen(characterList = PreviewSamodelkinViewModel(context).characterListState.collectAsState().value) {}
}