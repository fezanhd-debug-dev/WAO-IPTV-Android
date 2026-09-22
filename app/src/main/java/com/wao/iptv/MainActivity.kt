package com.wao.iptv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val vm: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaoTheme {
                val ctx = LocalContext.current
                val tv = remember { isTvMode(ctx, vm.settings.uiMode) }
                vm.homeRoute = if (tv) "tv_home" else "home"
                val nav = rememberNavController()

                NavHost(navController = nav, startDestination = "boot") {
                    composable("boot") { BootScreen(vm, nav) }
                    composable("login") { LoginScreen(vm, nav) }
                    composable("home") { HomeScreen(vm, nav) }
                    composable("live") { LiveScreen(vm, nav) }
                    composable("vod") { VodScreen(vm, nav) }
                    composable("settings") { SettingsScreen(vm, nav) }
                    composable("player") { PlayerScreen(vm, nav) }
                    composable("tv_home") { TvHomeScreen(vm, nav) }
                    composable("tv_quad") { TvQuadViewScreen(vm, nav) }
                }
            }
        }
    }
}
