package com.d121211079.maps.ui.activities.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211079.maps.data.models.Maps
import com.d121211079.maps.ui.activities.main.bottomBorder
import com.d121211079.maps.ui.theme.ValorantMapsApplicationTheme

class DetailActivity : ComponentActivity() {

    private var selectedMaps: Maps? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMaps = intent.getParcelableExtra("MAPS")
        setContent {
            ValorantMapsApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    private fun DetailScreen() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {
                TopAppBar(
                    title = {
                        Text(
                            text = "Back",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    modifier = Modifier.bottomBorder()
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = selectedMaps?.displayName.toString(),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(selectedMaps?.splash)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Ini gambar map",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    )

                    // Jika narrativeDescription != null maka ditampilkan
                    selectedMaps?.narrativeDescription?.let { narrative ->
                        Text(text = narrative)
                    }

                    // Jika displayIcon != null maka ditampilkan
                    selectedMaps?.displayIcon?.takeIf { it.isNotEmpty() }?.let { iconUrl ->
                        Text(
                            text = "MiniMap",
                            modifier = Modifier
                                .padding(vertical = 20.dp),
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(iconUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Ini gambar minimap",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun onBackPressed() {
    (LocalContext.current as? Activity)?.finish()
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomBorder(
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Gray
): Modifier = composed {
    drawBehind {
        drawLine(
            color = borderColor,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = borderWidth.toPx(),
        )
    }
}