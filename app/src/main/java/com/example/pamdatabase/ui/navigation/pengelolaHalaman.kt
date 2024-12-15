package com.example.pamdatabase.ui.navigation

import DetailMhsView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamdatabase.ui.view.mahasiswa.DestinasiInsert
import com.example.pamdatabase.ui.view.mahasiswa.HomeMhsView
import com.example.pamdatabase.ui.view.mahasiswa.InsertMhsView
import com.example.pamdatabase.ui.view.mahasiswa.UpdateMhsView

@Composable
fun pengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHome.route) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeMhsView(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println(
                        "PengelolaHalaman: nim = $nim"
                    )
                },
                onAddMhs = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        // Navigasi ke Detail Mahasiswa dengan argumen NIM
        composable(
            DestinasiDetail.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        // Perbaikan: Pastikan Anda menulis nama route yang benar
                        navController.navigate("${DetinasiUpdate.route}/$nim")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Perbaikan: Pastikan nama rute sesuai dengan yang didefinisikan di DestinasiUpdate
        composable(
            DetinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DetinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DetinasiUpdate.NIM)
            nim?.let {
                UpdateMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier,
                )
            }
        }
    }
}
