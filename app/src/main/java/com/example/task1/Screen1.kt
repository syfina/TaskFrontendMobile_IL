package com.example.task1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task1.ui.theme.Task1Theme



data class SkincareItem(val imageResId: Int, val name: String)

val skincareItems = listOf(
    SkincareItem(R.drawable.skincare1, "Bubble"),
    SkincareItem(R.drawable.skincare2, "Avoskin"),
    SkincareItem(R.drawable.skincare3, "Glossier"),
    SkincareItem(R.drawable.skincare4, "Tavi"),
    SkincareItem(R.drawable.skincare5, "TonyMoly"),
    SkincareItem(R.drawable.skincare6, "Skintific"),
    SkincareItem(R.drawable.skincare1, "Bubble"),
    SkincareItem(R.drawable.skincare2, "Avoskin"),
    SkincareItem(R.drawable.skincare3, "Glossier"),
    SkincareItem(R.drawable.skincare4, "Tavi"),
    SkincareItem(R.drawable.skincare5, "TonyMoly"),
    SkincareItem(R.drawable.skincare6, "Skintific")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1() {
    val items = listOf(
        BottomNavbarIcon("Home", Icons.Default.Home),
        BottomNavbarIcon("List", Icons.Default.List),
        BottomNavbarIcon("Profile", Icons.Default.Person)
    )

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Skincare List",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.purple_dark)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.purple_mid)
                )
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 15.dp)
                    .background(color = colorResource(id = R.color.bg)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(skincareItems.chunked(2)) { rowItems ->
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(rowItems) { item ->
                            SkincareItemCard(item)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        bottomBar = {
            CustomBottomNavigation(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
                items = items
            )
        }
    )
}

@Composable
fun CustomBottomNavigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    items: List<BottomNavbarIcon>
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

data class BottomNavbarIcon(val label: String, val icon: ImageVector)

@Composable
fun SkincareItemCard(item: SkincareItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(200.dp)
            .background(color = colorResource(id = R.color.purple_light), shape = RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.name,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}






@Preview(showBackground = true)
@Composable
fun Screen1Preview() {
    Task1Theme {
        Screen1()
    }
}