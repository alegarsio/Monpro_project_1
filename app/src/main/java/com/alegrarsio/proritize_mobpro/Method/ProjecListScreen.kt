package com.alegrarsio.proritize_mobpro.Method

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alegrarsio.proritize_mobpro.R
import com.alegrarsio.proritize_mobpro.model.Project
import com.alegrarsio.proritize_mobpro.navigation.Screen

@Composable
fun ProjectListScreen(navController: NavHostController, projects: List<Project>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        if (projects.isEmpty()) {
            Text(stringResource(R.string.tidak_ada_project))
        } else {
            projects.forEach { project ->
                Text("${project.name} (Prioritas: ${project.priority})")
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Screen.Add.Route) }) {
            Text(stringResource(R.string.tambah))
        }
    }
}