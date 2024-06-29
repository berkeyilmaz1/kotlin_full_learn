@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LemonApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonApp() {
    MyApplicationTheme {
        LemonScreens()
    }
}

@Composable
fun LemonAndTextImage(

    drawableResourceId: Int,
    textLabelResourceId: Int,
    onImageClick: () -> Unit,
) {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp)


            ) {
                Image(
                    painter = painterResource(id = drawableResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(128.dp)
                        .height(160.dp)
                        .padding(24.dp)
                )


            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(id = textLabelResourceId))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonScreens() {
    var currentStep by remember {
        mutableIntStateOf(value = 1)
    }
    var squeezeCount by remember {
        mutableIntStateOf(value = 0)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold
            )
        })
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentStep) {
                1 -> {
                    LemonAndTextImage(
                        drawableResourceId = R.drawable.lemon_tree,
                        textLabelResourceId = R.string.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()

                        }
                    )
                }

                2 -> {
                    LemonAndTextImage(
                        drawableResourceId = R.drawable.lemon_squeeze,
                        textLabelResourceId = R.string.lemon,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }


                        }
                    )
                }

                3 -> {
                    LemonAndTextImage(
                        drawableResourceId = R.drawable.lemon_drink,
                        textLabelResourceId = R.string.glass_of_lemonade,
                        onImageClick = {
                            currentStep = 4

                        }
                    )
                }

                4 -> {
                    LemonAndTextImage(
                        drawableResourceId = R.drawable.lemon_restart,
                        textLabelResourceId = R.string.empty_glass,
                        onImageClick = {
                            currentStep = 1

                        }
                    )
                }
            }
        }


    }


}




