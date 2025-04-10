package com.alegrarsio.proritize_mobpro.Method

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alegrarsio.proritize_mobpro.R
import com.alegrarsio.proritize_mobpro.model.Project
import com.alegrarsio.proritize_mobpro.navigation.Screen
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color


private fun convertPriorityStringToInt(priorityString: String): Int? {
    return when (priorityString.trim().lowercase()) {
        "besar" -> 3
        "sedang" -> 2
        "kecil" -> 1
        else -> null
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(
            text = stringResource(R.string.input_invvalid),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
    } else {
        Text(text = unit)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(navController: NavHostController, onAddProject: (Project) -> Unit) {
    var projectName by remember { mutableStateOf("") }
    var projectPriorityInput by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var priorityError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.tambah)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Back", tint = Color.Black)

                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = projectName,
                onValueChange = {
                    projectName = it
                    nameError = false
                },
                label = { Text(stringResource(R.string.Nama_Projek)) },
                trailingIcon = { IconPicker(nameError, "Project") },
                supportingText = { ErrorHint(nameError) },
                isError = nameError,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = projectPriorityInput,
                onValueChange = {
                    projectPriorityInput = it
                    priorityError = false
                },
                label = { Text(stringResource(R.string.Prioritas)) },
                trailingIcon = { IconPicker(priorityError, stringResource(R.string.Prioritas)) },
                supportingText = { ErrorHint(priorityError) },
                isError = priorityError,
                placeholder = { Text(stringResource(R.string.Helper_input)) },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val nameTrimmed = projectName.trim()
                val priorityTrimmed = projectPriorityInput.trim()
                val priorityValue = convertPriorityStringToInt(priorityTrimmed)

                nameError = nameTrimmed.isEmpty()
                priorityError = priorityTrimmed.isEmpty() || priorityValue == null

                if (!nameError && !priorityError) {
                    onAddProject(Project(name = nameTrimmed, priority = priorityValue!!))
                    projectName = ""
                    projectPriorityInput = ""
                    navController.navigate(Screen.Home.Route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }) {
                Text(stringResource(R.string.tambah))
            }
        }
    }
}

@Composable
fun IconButton(onClick: () -> Boolean, content: @Composable () -> Unit) {

}
