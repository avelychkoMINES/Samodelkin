package edu.mines.csci448.examples.samodelkin.presentation.newcharacter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun NewCharacterButton(text: String,
                       enabled: Boolean = true,
                       onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewNewCharacterButton() {
    NewCharacterButton(
        text = "New Character Button",
        enabled = true,
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewNewCharacterButtonDisabled() {
    NewCharacterButton(
        text = "New Character Button",
        enabled = false,
        onClick = {}
    )
}