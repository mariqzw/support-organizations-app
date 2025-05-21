package com.mariqzw.supportorganizationsapp.applications.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.applications.R
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ActionType
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationDetailsCompanionViewModel
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplicationCompanionDetailsScreen(
    navBackStackEntry: NavBackStackEntry?,
    viewModel: ApplicationDetailsCompanionViewModel = koinViewModel(),
    navController: NavController
) {
    val dimensions = LocalDimensions.current

    val id = navBackStackEntry?.arguments?.getLong("id")
    val date = navBackStackEntry?.arguments?.getString("date") ?: ""
    val time = navBackStackEntry?.arguments?.getString("time") ?: ""
    val startPoint = navBackStackEntry?.arguments?.getString("startPoint") ?: ""
    val endPoint = navBackStackEntry?.arguments?.getString("endPoint") ?: ""
    val status = navBackStackEntry?.arguments?.getString("status") ?: ""
    val comment = navBackStackEntry?.arguments?.getString("comment") ?: ""

    val alertMessage by viewModel.alertMessage.collectAsState()
    val navigateBack by viewModel.navigateBack.collectAsState()

    if (navigateBack) {
        navController.popBackStack()
        viewModel.onNavigated()
    }

    Surface(color = backgroundLight) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensions.verticalLarge, horizontal = dimensions.horizontalMedium),
            verticalArrangement = Arrangement.spacedBy(dimensions.verticalXLarge),
            horizontalAlignment = Alignment.Start
        ) {
            InfoRow(R.drawable.ic_outline_assignment,"Статус: ", status)
            InfoRow(R.drawable.calendar,"Дата: ", date)
            InfoRow(R.drawable.ic_time,"Время: ", time)
            InfoRow(R.drawable.ic_outline_my_location,"Станция отправления: ", startPoint)
            InfoRow(R.drawable.ic_outline_location_on,"Станция назначения: ", endPoint)
            InfoRow(R.drawable.chat_bubble,"Комментарий: ", comment)

            Spacer(modifier = Modifier.height(dimensions.verticalLarge))

            Row {
                when (status) {
                    "Создана" -> {
                        OutlinedButton(
                            onClick = {
                                id?.let {
                                    viewModel.onActionClick(it, status, ActionType.REJECT)
                                }
                            }
                        ) {
                            Text(text = "Отклонить")
                        }

                        Spacer(Modifier.width(dimensions.horizontalMedium))

                        PrimaryButton(
                            text = "Взять на выполнение",
                            onButtonClick = {
                                id?.let {
                                    viewModel.onActionClick(it, status, ActionType.ASSIGN)
                                }
                            }
                        )
                    }

                    "Назначена" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PrimaryButton(
                                text = "Завершить",
                                onButtonClick = {
                                    id?.let {
                                        viewModel.onActionClick(it, status, ActionType.COMPLETE)
                                    }
                                }
                            )
                        }
                    }

                    else -> {
                    }
                }
            }

            if (alertMessage != null) {
                AlertDialog(
                    onDismissRequest = { viewModel.dismissAlert() },
                    confirmButton = {
                        TextButton(onClick = { viewModel.dismissAlert() }) {
                            Text("ОК")
                        }
                    },
                    text = {
                        Text(alertMessage ?: "")
                    }
                )
            }
        }
    }
}

@Composable
private fun InfoRow(icon: Int, label: String, value: String) {
    val dimensions = LocalDimensions.current
    Row(modifier = Modifier.padding(horizontal = dimensions.horizontalXSmall)) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            modifier = Modifier
                .padding(end = dimensions.horizontalSmall)
                .height(dimensions.applicationsIconSize)
        )
        Text(text = label, style = MediumRoboto16)
        Text(text = value, style = RegularRoboto16)
    }
}
