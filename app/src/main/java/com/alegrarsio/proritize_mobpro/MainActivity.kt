package com.alegrarsio.proritize_mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alegrarsio.proritize_mobpro.Data_Structure.Tree
import com.alegrarsio.proritize_mobpro.model.Project
import com.alegrarsio.proritize_mobpro.ui.theme.Proritize_MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proritize_MobproTheme {
                ProjectPriorityScreen()
            }
        }
    }
}



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

            TopAppBar(title = { Text("Daftar Proyek Berdasarkan Prioritas") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Proyek (Diurutkan berdasarkan Prioritas):",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
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
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Proritize_MobproTheme {
        Greeting("Android")
    }
}