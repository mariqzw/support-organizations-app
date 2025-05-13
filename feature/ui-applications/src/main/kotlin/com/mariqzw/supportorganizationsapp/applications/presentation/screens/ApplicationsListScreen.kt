package com.mariqzw.supportorganizationsapp.applications.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mariqzw.supportorganizationsapp.ui.components.cards.PrimaryCard
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight

@Composable
fun ApplicationsListScreen(navController: NavController) {
    val dimensions = LocalDimensions.current

    Surface(
        color = backgroundLight
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.horizontalMedium),
            verticalArrangement = Arrangement.spacedBy(dimensions.verticalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                PrimaryCard(
                    date = "29.04.2025",
                    status = "Принята",
                    startPoint = "Домодедовская",
                    endPoint = "Саларьево",
                )
            }

            item {
                PrimaryCard(
                    date = "02.03.2025",
                    status = "Закрыта",
                    startPoint = "Алтуфьево",
                    endPoint = "Электрозаводская"
                )
            }

            item {
                PrimaryCard(
                    date = "17.12.2024",
                    status = "Закрыта",
                    startPoint = "Маяковская",
                    endPoint = "Электрозаводская"
                )
            }

            item {
                PrimaryCard(
                    date = "17.11.2024",
                    status = "Закрыта",
                    startPoint = "Чистые пруды",
                    endPoint = "Алексеевская"
                )
            }

            item {
                PrimaryCard(
                    date = "29.10.2024",
                    status = "Закрыта",
                    startPoint = "Алтуфьево",
                    endPoint = "Фонвизинская"
                )
            }

            item {
                PrimaryCard(
                    date = "24.10.2024",
                    status = "Закрыта",
                    startPoint = "Новые Черемушки",
                    endPoint = "Электрозаводская"
                )
            }

            item {
                PrimaryCard(
                    date = "02.11.2023",
                    status = "Закрыта",
                    startPoint = "Лефортово",
                    endPoint = "Электрозаводская"
                )
            }

            item {
                PrimaryCard(
                    date = "19.06.2023",
                    status = "Закрыта",
                    startPoint = "Зюзино",
                    endPoint = "Севастопольская"
                )
            }

            item {
                PrimaryCard(
                    date = "01.06.2023",
                    status = "Закрыта",
                    startPoint = "Красногвардейская",
                    endPoint = "Электрозаводская"
                )
            }

            item {
                PrimaryCard(
                    date = "13.06.2023",
                    status = "Закрыта",
                    startPoint = "Саларьево",
                    endPoint = "Сокольники"
                )
            }

            item {
                PrimaryCard(
                    date = "12.02.2023",
                    status = "Закрыта",
                    startPoint = "Бибирево",
                    endPoint = "ВДНХ"
                )
            }
        }
    }
}

@Composable
@Preview
fun ApplicationsListScreenPreview() {
    val navController = rememberNavController()
    ApplicationsListScreen(
        navController = navController
    )
}
