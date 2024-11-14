package com.example.task1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task1.ui.theme.Task1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(onBackPressed: () -> Unit) {
    val items = listOf(
        BottomNaviIcon("Home", Icons.Default.Home),
        BottomNaviIcon("List", Icons.Default.List),
        BottomNaviIcon("Profile", Icons.Default.Person)
    )

    var selectedItem by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Skincare Details",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.purple_dark)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = colorResource(R.color.purple_dark)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.purple_mid)
                )
            )
        },
        content = { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(100.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.skincare1),
                            contentDescription = "Bubble Image",
                            modifier = Modifier
                                .size(350.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )


                        Text(
                            text = "Bubble",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp)
                        )


                        Text(
                            text = "Bubble Skincare is a skincare brand that creates plant-based, cruelty-free products that are designed to be affordable, effective, and high-quality.",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Bubble products are made with plant-based glycerin to concentrate herbal-based flowers, plants, and sea-derived extracts and waters. They are free of colors, fillers, fragrance, animal-derived ingredients, essential oils, parabens, formaldehyde releasers, and sulfates.",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationn(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
                items = items
            )
        }
    )
}

@Composable
fun BottomNavigationn(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    items: List<BottomNaviIcon>
) {
    BottomAppBar(
        containerColor = colorResource(id = R.color.purple_mid),
        contentColor = colorResource(id = R.color.purple_dark)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

data class BottomNaviIcon(val label: String, val icon: ImageVector)


@Preview(showBackground = true)
@Composable
fun Screen2Preview() {
    Task1Theme {
        Screen2(onBackPressed = {})
    }
}