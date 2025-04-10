package com.alegrarsio.proritize_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alegrarsio.proritize_mobpro.Method.AddProjectScreen
import com.alegrarsio.proritize_mobpro.Method.ProjectListScreen
import com.alegrarsio.proritize_mobpro.Method.ProjectPriorityScreen
import com.alegrarsio.proritize_mobpro.model.Project

@Composable
fun SetUpNavGraph(navController : NavHostController = rememberNavController()){
    val projects = remember { mutableStateListOf<Project>() }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.Route
    ){
        composable(route = Screen.Home.Route){
            ProjectPriorityScreen(navController = navController, projects = projects)
        }
        composable(route = Screen.Add.Route) {
            AddProjectScreen(navController = navController) { project ->
                projects.add(project)
            }
        }
        composable(route = Screen.List.Route) {
            ProjectListScreen(navController = navController, projects = projects)
        }
    }
}