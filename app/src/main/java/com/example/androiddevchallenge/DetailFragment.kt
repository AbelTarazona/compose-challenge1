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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.bkYellow
import com.example.androiddevchallenge.ui.theme.redSelected
import com.example.androiddevchallenge.ui.theme.redSelectedBackground

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    DetailApp()
                }
            }
        }
    }

    @Composable
    fun DetailApp() {
        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Header()
                PetMainInformation()
                PetGallery(modifier = Modifier.padding(start = 20.dp, top = 15.dp))
                PetDescription()
                Spacer(modifier = Modifier.weight(1f))
                ButtonAdopt()
            }
        }
    }

    @Composable
    fun Header() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_left_24),
                contentDescription = null
            )
            Box(
                modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
                    .background(redSelectedBackground),
                contentAlignment = Alignment.Center,

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }

    @Composable
    fun PetMainInformation() {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Sparky", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Text(
                    text = "Golden Retriever",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
                        contentDescription = null,
                        modifier = Modifier.scale(0.8f)
                    )
                    Text(
                        text = "2.5 kms away",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Image(
                    painter = painterResource(id = R.drawable.ic_male),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(text = "8 months old", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
    }

    @Composable
    fun PetGallery(modifier: Modifier = Modifier) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            PetGalleryVertical()
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            PetGalleryMain(modifier = Modifier.weight(1f))
        }
    }

    @Composable
    fun PetGalleryMain(modifier: Modifier) {
        Box(modifier = modifier, contentAlignment = Alignment.CenterEnd) {
            Canvas(
                modifier = Modifier.matchParentSize().scale(2f).offset(x = 40.dp),
                onDraw = { drawCircle(color = bkYellow) }
            )

            Image(
                painter = painterResource(id = R.drawable.dogtest),
                contentDescription = null,
                modifier = Modifier.scale(1.8f)
            )
        }
    }

    @Composable
    fun PetGalleryVertical() {
        val list = getListHorizontalGallery()
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            list.forEach { img ->
                MiniImageGallery(img = img)
            }
        }
    }

    @Composable
    fun MiniImageGallery(img: Int) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            ),
            modifier = Modifier.size(65.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null, alignment = Alignment.Center
            )
        }
    }

    @Composable
    fun PetDescription() {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp)) {
            Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = stringResource(id = R.string.testDescription),
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
    }

    @Composable
    fun ButtonAdopt() {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(topStart = 35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = redSelected),
                modifier = Modifier.width(150.dp).height(60.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_pets_24),
                        contentDescription = null,
                        modifier = Modifier.scale(0.6f)
                    )
                    Text(
                        text = "ADOPT",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            DetailApp()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            DetailApp()
        }
    }

    private fun getListHorizontalGallery() = listOf(
        R.drawable.pet1,
        R.drawable.pet2,
        R.drawable.pet3,
        R.drawable.pet4,
    )
}
