package com.alegrarsio.proritize_mobpro.Method

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alegrarsio.proritize_mobpro.Data_Structure.Tree
import com.alegrarsio.proritize_mobpro.R
import com.alegrarsio.proritize_mobpro.model.Project


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectPriorityScreen() {

    val projectTree = remember {
        val tree = Tree()
        tree.insert(Project(name = "Implement Login Feature", priority = 2))
        tree.insert(Project(name = "Design Database Schema", priority = 1))
        tree.insert(Project(name = "Setup CI/CD Pipeline", priority = 3))
        tree.insert(Project(name = "Develop User Profile Page", priority = 2))
        tree.insert(Project(name = "Write API Documentation", priority = 3))
        tree.insert(Project(name = "Fix Critical Bug #123", priority = 1))
        tree
    }

    val displayedProjects = remember {
        mutableStateOf(projectTree.inOrder())
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = { Text(text = stringResource(R.string.judul_header)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Card (
                modifier =  Modifier.padding(40.dp)
            ){
                Text(
                    text = stringResource(R.string.header_prioritas_title),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            if (displayedProjects.value.isEmpty()) {
                Text("Tidak ada proyek.")
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(displayedProjects.value, key = { project -> project.id }) { project ->
                        ProjectItem(project = project)

                    }
                }
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project) {
    Card(
        modifier =  Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp) // Tambahkan padding di dalam Card
        ) {
            Text(
                text = project.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prioritas: ${project.priority}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
