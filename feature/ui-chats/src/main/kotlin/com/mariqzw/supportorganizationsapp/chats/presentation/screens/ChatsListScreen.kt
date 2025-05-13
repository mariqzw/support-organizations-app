package com.mariqzw.supportorganizationsapp.chats.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.ui.components.cards.Message
import com.mariqzw.supportorganizationsapp.ui.components.cards.MessageItem
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.outlineVariantLight

@Composable
fun ChatsListScreen() {

    val dimensions = LocalDimensions.current

    val messages = remember {
        listOf(
            Message("Борис Иванов", "Добрый день! Через 5 минут подойду ко входу. Постараюсь как можно скорее прибыть на место", "12:40"),
            Message("Дмитрий Клюев", "Здравствуйте, ожидаю вас на станции Беговая", "11:33"),
            Message("Сергей Кирьянов", "Добрый вечер, хорошо. Я скоро буду", "19:56"),
            Message("Матвей Демидов", "Добрый вечер, принято", "20:00"),
            Message("Сергей Скворцов", "Здравствуйте", "20:20"),
            Message("Устин Панов", "Добрый вечер! Сейчас подойду", "20:11"),
            Message("Константин Синицын", "Здравствуйте! Ожидаю вас у входа на станцию Бутырская.", "10:06"),
            Message("Алексей Синицын", "Здравствуйте! Ожидаю вас у входа на станцию Бутырская.", "10:06"),
            Message("Константин Синицын", "Здравствуйте! Ожидаю вас у входа на станцию Бутырская.", "10:06"),
            Message("Константин Синицын", "Здравствуйте! Ожидаю вас у входа на станцию Бутырская.", "10:06"),
            Message("Константин Синицын", "Здравствуйте! Ожидаю вас у входа на станцию Бутырская.", "10:06"),
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundLight)
            .padding(vertical = dimensions.verticalMedium, horizontal = dimensions.horizontalMedium),
        verticalArrangement = Arrangement.spacedBy(dimensions.verticalXXSmall)
    ) { itemsIndexed(messages) { index, message ->
            MessageItem(message)
            if (index < messages.size - 1) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = outlineVariantLight.copy(alpha = 0.4f)
                )
            }
        }
    }
}

@Composable
@Preview
fun ChatsListScreenPreview() {
    ChatsListScreen()
}
