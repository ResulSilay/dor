package org.dor.presentation.screen.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.dor.domain.model.IPInfo
import org.dor.getPlatform
import org.dor.presentation.ui.component.InfoItem
import org.dor.presentation.ui.component.InfoText
import org.dor.presentation.ui.component.LogoText
import org.dor.presentation.ui.component.ScanEffect
import org.dor.presentation.ui.component.SearchBox
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen() {
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    var ipAddress by remember { mutableStateOf("") }
    var isShowInfoCard by remember { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(uiState.ipAddress) {
        ipAddress = uiState.ipAddress
    }

    LaunchedEffect(uiState.isLoading) {
        isShowInfoCard = !uiState.isLoading
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            SearchTopBar()
        },
        bottomBar = {
            if (uiState.errorMessage.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 24.dp),
                    text = uiState.errorMessage,
                    color = Color(color = 0xFFFF6F61),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isShowInfoCard) {
                ModalBottomSheet(
                    onDismissRequest = {
                        isShowInfoCard = false
                    },
                    sheetState = bottomSheetState,
                    dragHandle = { },
                    containerColor = Color.Transparent
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.padding(bottom = 32.dp),
                        visible = true,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> fullHeight },
                            animationSpec = tween(durationMillis = 500)
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { fullHeight -> fullHeight },
                            animationSpec = tween(durationMillis = 500)
                        )
                    ) {
                        InfoCard(info = uiState.ipInfo)
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (!isShowInfoCard) {
                    ScanEffect(
                        isActive = uiState.isLoading,
                        searchContent = {
                            SearchBox(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                value = ipAddress,
                                onValueChange = { value ->
                                    ipAddress = value
                                },
                                onSearch = {
                                    viewModel.search(ipAddress = ipAddress)
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoText()

        InfoText(
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 24.dp
            ),
            text = "View location, network, and service details of your device or any queried IP address."
        )
    }
}

@Composable
private fun InfoCard(info: IPInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(all = 16.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.12f),
                        Color.White.copy(alpha = 0.05f)
                    )
                )
            )
            .padding(vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 12.dp),
            text = "Information",
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

        InfoItem(title = "IP Address", value = info.query)
        InfoItem(title = "Country", value = info.country)
        InfoItem(title = "Country Code", value = info.countryCode)
        InfoItem(title = "Region", value = "${info.region} (${info.regionName})")
        InfoItem(title = "City", value = info.city)
        InfoItem(title = "ZIP", value = info.zip)
        InfoItem(title = "Latitude / Longitude", value = "${info.lat}, ${info.lon}")
        InfoItem(title = "Timezone", value = info.timezone)
        InfoItem(title = "ISP", value = info.isp)
        InfoItem(title = "Org", value = info.org)
        InfoItem(title = "AS", value = info.asInfo)
        InfoItem(title = "Platform", value = getPlatform().name, isDividerVisible = false)
    }
}
