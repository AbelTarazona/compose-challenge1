package com.example.androiddevchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
            Column {
                Header()
                PetMainInformation()
                PetGallery()
                PetDescription()
                ButtonAdopt()
            }
        }
    }

    @Composable
    fun Header() {
        Text(text = "TESTING NAVIGATION")
    }

    @Composable
    fun PetMainInformation() {

    }

    @Composable
    fun PetGallery() {

    }

    @Composable
    fun PetDescription() {

    }

    @Composable
    fun ButtonAdopt() {

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

}