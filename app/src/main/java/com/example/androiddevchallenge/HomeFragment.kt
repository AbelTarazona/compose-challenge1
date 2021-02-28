/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.bkGreen
import com.example.androiddevchallenge.ui.theme.bkGreenLight
import com.example.androiddevchallenge.ui.theme.bkLightBlue
import com.example.androiddevchallenge.ui.theme.bkRed
import com.example.androiddevchallenge.ui.theme.bkYellow
import com.example.androiddevchallenge.ui.theme.redSelected

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Header()
                Column(
                    modifier = Modifier.fillMaxSize().background(
                        color = colorResource(id = R.color.gray2),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                ) {
                    Tab(list = getListCategoryAnimals())
                    ListPet(list = getPetsByCategory())
                }
            }
        }
    }

    @Composable
    fun ListPet(list: List<Pet>) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            list.forEach { pet ->
                CardPet(pet)
            }
        }
    }

    @Composable
    fun CardPet(pet: Pet) {
        Surface(
            shape = RoundedCornerShape(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable {
                    findNavController().navigate(R.id.viewToDetail)
                }
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                BoxDogImage(pet.backgroundColor, pet.img)
                BoxDogInformation(pet)
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = if (pet.isFavorite) painterResource(id = R.drawable.ic_baseline_favorite_24) else painterResource(
                        id = R.drawable.ic_baseline_favorite_border_24
                    ),
                    contentDescription = null
                )
            }
        }
    }

    @Composable
    fun BoxDogInformation(pet: Pet) {
        Column(modifier = Modifier.padding(horizontal = 10.dp).height(100.dp)) {
            Text(text = pet.name, fontWeight = FontWeight.Bold, fontSize = 19.sp)
            Text(text = pet.race, fontSize = 13.sp)
            Text(text = "${pet.sex}, ${pet.age}", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
                    contentDescription = null,
                    modifier = Modifier.scale(0.8f)
                )
                Text(
                    text = "${pet.distance} kms away",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }

    @Composable
    fun BoxDogImage(backgroundColor: Color, img: Int) {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(15.dp),
            backgroundColor = backgroundColor,
            modifier = Modifier.size(100.dp),
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier.offset(y = 15.dp)
            )
        }
    }

    @Composable
    fun Tab(list: List<Category>) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChipFilter()
            list.forEach { category ->
                ChipCategory(category)
            }
        }
    }

    @Composable
    fun ChipFilter() {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                    contentDescription = null
                )
            }
        }
    }

    @Composable
    fun ChipCategory(category: Category) {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(15.dp),
            backgroundColor = if (category.isSelected) redSelected else Color.White
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = category.img),
                    contentDescription = null,
                    colorFilter = if (category.isSelected) ColorFilter.tint(Color.White) else ColorFilter.tint(
                        Color.Black
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = category.title,
                    modifier = Modifier.padding(start = 10.dp, end = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (category.isSelected) Color.White else Color.Black
                )
            }
        }
    }

    @Composable
    fun Header() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                contentDescription = null
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Location", fontSize = 13.sp, color = Color.Gray)
                Text(text = "Lima C., Perú", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(id = R.drawable.person),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clickable(onClick = {})
            )
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyApp()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyApp()
        }
    }

    data class Category(
        @DrawableRes val img: Int,
        val title: String,
        val isSelected: Boolean = false
    )

    data class Pet(
        @DrawableRes val img: Int = R.drawable.dogtest,
        val backgroundColor: Color,
        val name: String,
        val race: String,
        val sex: String,
        val age: String,
        val distance: Double,
        val isFavorite: Boolean = false
    )

    fun getListCategoryAnimals() = listOf(
        Category(img = R.drawable.dog, title = "Dogs", isSelected = true),
        Category(img = R.drawable.bird, title = "Birds"),
        Category(img = R.drawable.butterfly, title = "Butterfly"),
        Category(img = R.drawable.fish, title = "Fish"),
        Category(img = R.drawable.monkey, title = "Monkey"),
        Category(img = R.drawable.parrot, title = "Parrot"),
        Category(img = R.drawable.chicken, title = "Chicken"),
    )

    fun getPetsByCategory() = listOf(
        Pet(
            name = "Sparky",
            race = "Golden Retriever",
            sex = "Female",
            age = "8 months old",
            distance = 2.5,
            isFavorite = true,
            backgroundColor = bkYellow
        ),
        Pet(
            name = "Charlie",
            race = "Boston Terrier",
            sex = "Male",
            age = "1.5 years old",
            distance = 2.6,
            backgroundColor = bkGreen,
            img = R.drawable.boston
        ),
        Pet(
            name = "Max",
            race = "Siberian Husky",
            sex = "Male",
            age = "1 year old",
            distance = 2.9,
            backgroundColor = bkLightBlue,
            img = R.drawable.husky
        ),
        Pet(
            name = "Daisy",
            race = "Maltese",
            sex = "Female",
            age = "7 month old",
            distance = 3.1,
            backgroundColor = bkRed,
            img = R.drawable.maltese
        ),
        Pet(
            name = "Zoe",
            race = "Jack Russel Terrier",
            sex = "Male",
            age = "7 year old",
            distance = 1.1,
            backgroundColor = bkGreenLight,
            img = R.drawable.rusell
        ),
    )
}
