package com.mariqzw.supportorganizationsapp.applications.presentation.components.pagers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.SupportOrganizationsAppTheme
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceLight
import kotlinx.coroutines.launch

@Composable
fun TabRowPager(
    tabs: List<Pair<String, @Composable () -> Unit>>,
    modifier: Modifier = Modifier,
    selectedTabIndex: Int = 0,
    maxLinesInTitle: Int = 1,
    spaceBetweenTabAndPager: Dp = 0.dp,
    horizontalSpaceBetweenContent: Dp = 0.dp
) {
    val dimensions = LocalDimensions.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = selectedTabIndex,
        initialPageOffsetFraction = 0f
    ) {
        tabs.size
    }
    val textMeasurer = rememberTextMeasurer()
    val textSizes = remember { mutableStateListOf<Size>() }

    Column(modifier = modifier) {
        TabRow(
            modifier = Modifier
                .padding(horizontalSpaceBetweenContent),
            containerColor = surfaceLight,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPosition ->
                val textSize = textSizes.getOrNull(pagerState.currentPage) ?: Size(50f, 0f)
                val width = with(LocalDensity.current) { textSize.width.toDp() }
                PrimaryIndicator(
                    modifier = Modifier
                        .height(dimensions.circularStrokeWith)
                        .tabIndicatorOffset(tabPosition[pagerState.currentPage])
                        .padding(vertical = dimensions.verticalXSmall),
                    width = width,
                    color = primaryLight
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier.background(color = surfaceLight),
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tab.first,
                            maxLines = maxLinesInTitle,
                            style = MediumRoboto14,
                            modifier = Modifier.onGloballyPositioned {
                                val measuredSize = textMeasurer.measure(tab.first).size
                                if (textSizes.size > index) {
                                    textSizes[index] = measuredSize.toSize()
                                } else {
                                    textSizes.add(measuredSize.toSize())
                                }
                            },
                            color = if (pagerState.currentPage == index) onSurfaceLight else onSurfaceLight
                        )
                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalSpaceBetweenContent)
                    .padding(top = spaceBetweenTabAndPager)
            ) { tabs[page].second() }
        }
    }
}

@Composable
@Preview
fun TabRowPagerPreview() {
    SupportOrganizationsAppTheme {
        Surface {
            TabRowPager(
                tabs = listOf(
                    "Свободные заявки" to {
                        Text("test")
                    },"Мои заявки" to {
                        Text("test")
                    }
                ),
                spaceBetweenTabAndPager = 8.dp
            )
        }
    }
}
