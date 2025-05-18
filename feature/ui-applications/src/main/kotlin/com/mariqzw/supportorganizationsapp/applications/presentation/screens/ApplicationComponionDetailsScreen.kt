package com.mariqzw.supportorganizationsapp.applications.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsCompanionListViewModel
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsListViewModel
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplicationComponionDetailsScreen(
    navController: NavController?,
    navBackStackEntry: NavBackStackEntry?
) {
    val dimensions = LocalDimensions.current

    val date = navBackStackEntry?.arguments?.getString("date") ?: ""
    val time = navBackStackEntry?.arguments?.getString("time") ?: ""
    val startPoint = navBackStackEntry?.arguments?.getString("startPoint") ?: ""
    val endPoint = navBackStackEntry?.arguments?.getString("endPoint") ?: ""
    val status = navBackStackEntry?.arguments?.getString("status") ?: ""
    val comment = navBackStackEntry?.arguments?.getString("comment") ?: ""

    Surface(
        color = backgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensions.verticalMedium,
                    horizontal = dimensions.horizontalSmall
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensions.verticalLarge,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Статус: ", style = MediumRoboto16)
                Text(text = status, style = RegularRoboto16)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Дата: ", style = MediumRoboto16)
                Text(text = date, style = RegularRoboto16)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Время: ", style = MediumRoboto16)
                Text(text = time, style = RegularRoboto16)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Станция отправления: ", style = MediumRoboto16)
                Text(text = startPoint, style = RegularRoboto16)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Станция назначения: ", style = MediumRoboto16)
                Text(text = endPoint, style = RegularRoboto16)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.horizontalXSmall)
            ) {
                Text(text = "Комментарий: ", style = MediumRoboto16)
                Text(text = comment, style = RegularRoboto16)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row {
                OutlinedButton(onClick = { }) {
                    Text(text = "Отменить")
                }

                Spacer(Modifier.width(dimensions.horizontalMedium))

                Button(onClick = { }) {
                    Text(text = "Назначить мне")
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun ApplicationDetailsScreenPreview() {
//    ApplicationDetailsScreen(
//        navController = null,
//        navBackStackEntry = null
//    )
//}
