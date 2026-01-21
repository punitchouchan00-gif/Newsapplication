package com.example.newsapplication.presentation.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.newsapplication.Data.Dto.Results
import com.example.newsapplication.Util.Result
import com.example.newsapplication.presentation.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsState by viewModel.newsState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Latest", "Popular", "Old")

    LaunchedEffect(Unit) {
        viewModel.getNewsArticles("in")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daily News") },
                actions = {
                    IconButton(onClick = { /* search */ }) {
                        Icon(Icons.Default.Search, null)
                    }
                    IconButton(onClick = { /* notification */ }) {
                        Icon(Icons.Default.Notifications, null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // 🔍 Search bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search news...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            // 🏷 Category Tabs
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            when (newsState) {

                is Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {

                    val articles =
                        (newsState as Result.Success<List<Results>>).Data.take(3)

                    LazyColumn {
                        items(articles) { item ->
                            NewsItem(item)
                        }
                    }
                }

                is Result.Error -> {
                    Text(
                        text = (newsState as Result.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {}
            }
        }
    }
}
