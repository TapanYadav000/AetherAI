package com.tapan.aetherai.presentation.onboarding.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapan.aetherai.domain.model.PersonalityTrait

@Composable
fun TraitSelectionPage(
    onValidationChanged: (Boolean) -> Unit
) {

    val traits = PersonalityTrait.entries

    var selectedTraits by remember {
        mutableStateOf(setOf<PersonalityTrait>())
    }

    LaunchedEffect(selectedTraits) {
        onValidationChanged(
            selectedTraits.size == 3
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Choose 3 traits",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(traits) { trait ->

                FilterChip(
                    selected = selectedTraits.contains(trait),
                    onClick = {

                        selectedTraits = when {

                            selectedTraits.contains(trait) ->
                                selectedTraits - trait

                            selectedTraits.size < 3 ->
                                selectedTraits + trait

                            else ->
                                selectedTraits
                        }
                    },
                    label = {
                        Text(trait.name)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${selectedTraits.size}/3 selected"
        )
    }
}