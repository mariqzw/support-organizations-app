package com.mariqzw.supportorganizationsapp.applications.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsListState
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsListViewModel
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.ui.components.cards.PrimaryCard
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplicationsListScreen(
    navController: NavController,
    viewModel: ApplicationsListViewModel = koinViewModel()
) {
    val dimensions = LocalDimensions.current
    val applications by viewModel.applications.collectAsState()
    val state by viewModel.state.collectAsState(initial = ApplicationsListState.Init)

    Surface(color = backgroundLight) {
        when (state) {
            is ApplicationsListState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }

            is ApplicationsListState.Error -> {
                val message = (state as ApplicationsListState.Error).message
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensions.verticalMedium),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = message ?: "Ошибка при загрузке")
                        Button(onClick = { viewModel.refresh() }) {
                            Text(text = "Повторить")
                        }
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensions.horizontalMedium),
                    verticalArrangement = Arrangement.spacedBy(dimensions.verticalMedium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(applications.size) { index ->
                        val app = applications[index]
                        PrimaryCard(
                            date = app.date,
                            status = app.status,
                            startPoint = app.departureStation,
                            endPoint = app.destinationStation,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Route.ApplicationDetailsScreen(
                                            date = app.date,
                                            time = app.time,
                                            startPoint = app.departureStation,
                                            endPoint = app.destinationStation,
                                            status = app.status,
                                            comment = app.comment ?: ""
                                        )
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}


//            item {
//                PrimaryCard(
//                    date = "29.04.2025",
//                    status = "Принята",
//                    startPoint = "Домодедовская",
//                    endPoint = "Саларьево",
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "02.03.2025",
//                    status = "Закрыта",
//                    startPoint = "Алтуфьево",
//                    endPoint = "Электрозаводская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "17.12.2024",
//                    status = "Закрыта",
//                    startPoint = "Маяковская",
//                    endPoint = "Электрозаводская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "17.11.2024",
//                    status = "Закрыта",
//                    startPoint = "Чистые пруды",
//                    endPoint = "Алексеевская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "29.10.2024",
//                    status = "Закрыта",
//                    startPoint = "Алтуфьево",
//                    endPoint = "Фонвизинская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "24.10.2024",
//                    status = "Закрыта",
//                    startPoint = "Новые Черемушки",
//                    endPoint = "Электрозаводская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "02.11.2023",
//                    status = "Закрыта",
//                    startPoint = "Лефортово",
//                    endPoint = "Электрозаводская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "19.06.2023",
//                    status = "Закрыта",
//                    startPoint = "Зюзино",
//                    endPoint = "Севастопольская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "01.06.2023",
//                    status = "Закрыта",
//                    startPoint = "Красногвардейская",
//                    endPoint = "Электрозаводская"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "13.06.2023",
//                    status = "Закрыта",
//                    startPoint = "Саларьево",
//                    endPoint = "Сокольники"
//                )
//            }
//
//            item {
//                PrimaryCard(
//                    date = "12.02.2023",
//                    status = "Закрыта",
//                    startPoint = "Бибирево",
//                    endPoint = "ВДНХ"
//                )
//            }

@Composable
@Preview
fun ApplicationsListScreenPreview() {
    val navController = rememberNavController()
    ApplicationsListScreen(
        navController = navController
    )
}
