package com.alegrarsio.proritize_mobpro.Method

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alegrarsio.proritize_mobpro.Data_Structure.Tree
import com.alegrarsio.proritize_mobpro.R
import com.alegrarsio.proritize_mobpro.model.Project
import com.alegrarsio.proritize_mobpro.navigation.Screen

@Composable
fun ConvertPriority(priority: Int): String {
    return when (priority) {
        1 -> stringResource(R.string.Ringan)
        2 -> stringResource(R.string.Sedang)
        3 -> stringResource(R.string.Besar)
        else -> "Tidak Ditentukan"
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectPriorityScreen(navController: NavHostController, projects: MutableList<Project>) {

    val projectTree = Tree()

    projects.forEach { project ->
        projectTree.insert(project)
    }

    val orderedProjects = projectTree.inOrder()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.Prioritas)) },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Add.Route) }) {
                        Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.tambah))
                    }

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (orderedProjects.isEmpty()) {
                Text(text = stringResource(R.string.tidak_ada_project))
            } else {
                orderedProjects.takeLast(5).toMutableList().forEach { project ->
                    val priorityText = ConvertPriority(project.priority)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f).padding(16.dp)
                            ) {
                                Text("${project.name} (Prioritas: ${priorityText})")
                            }
                            IconButton(onClick = {
                                projectTree.delete(project)
                                projects.remove(project)
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = stringResource(R.string.Hapus))
                            }
                        }
                    }
                }
            }
        }
    }
}
