package com.alegrarsio.proritize_mobpro.navigation

sealed class  Screen(val Route : String)
{
    data object Home : Screen("mainScreen")
    data object Add : Screen("addScreen")
    data object List : Screen("list")
}