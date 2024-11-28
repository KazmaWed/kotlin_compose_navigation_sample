package com.example.composenavigationsample.ui.navigatorScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.composenavigationsample.ui.NavigatorScreens
import com.example.composenavigationsample.ui.mainScreen.MainScreenViewModel

@Composable
fun NavigatorScreen(
    count: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { safeInsets ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(safeInsets)
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    NavigatorScreens.Page(count).title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    val nextPage = NavigatorScreens.Page(count + 1)
                    navController.navigate(nextPage.route)
                }
            ) {
                Text("次のページ")
            }
        }
    }
}