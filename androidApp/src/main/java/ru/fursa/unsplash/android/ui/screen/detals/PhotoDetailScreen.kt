package ru.fursa.unsplash.android.ui.screen.detals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.image.UserAvatarItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoDetailScreen(navController: NavController) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { navController.navigateUp() }
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = painterResource(id = R.drawable.ic_download), contentDescription = ""
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                UserAvatarItem(
                    modifier = Modifier.size(45.dp),
                    avatarUrl = "https://shorturl.at/gjzDI"
                )
                Text(
                    text = stringResource(id = R.string.user_name),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )
                )
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_download),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Image(imageVector = Icons.Default.Favorite, contentDescription = "")
                    Spacer(modifier = Modifier.size(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_bookmark_add),
                        contentDescription = ""
                    )
                }
            }

            Divider(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp
                ),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.views), fontWeight = FontWeight.Medium)
                    Text(text = "20k")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.downloads), fontWeight = FontWeight.Medium)
                    Text(text = "2k")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.likes), fontWeight = FontWeight.Medium)
                    Text(text = "15k")
                }
            }

            Divider(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp
                ),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = stringResource(id = R.string.camera), fontWeight = FontWeight.Medium)
                    Text(text = "Canon EOS 500D")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(id = R.string.exposition), fontWeight = FontWeight.Medium)
                    Text(text = "1/400s")
                }

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = stringResource(id = R.string.focus), fontWeight = FontWeight.Medium)
                    Text(text = "70.0mm")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(id = R.string.iso), fontWeight = FontWeight.Medium)
                    Text(text = "200")
                }

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = stringResource(id = R.string.apertura), fontWeight = FontWeight.Medium)
                    Text(text = "f/3.2")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(id = R.string.resolution), fontWeight = FontWeight.Medium)
                    Text(text = "2000х3000")
                }
            }

            Divider(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp
                ),
                thickness = 1.dp,
                color = Color.LightGray
            )

            LazyRow(
                contentPadding = PaddingValues(16.dp), content = {
                    item {
                        Chip(onClick = { /*TODO*/ }) {
                            Text(text = "hard work")
                        }
                    }
                    item {
                        Chip(onClick = { /*TODO*/ }) {
                            Text(text = "life style")
                        }
                    }
                    item {
                        Chip(onClick = { /*TODO*/ }) {
                            Text(text = "exploring")
                        }
                    }
                }
            )

            Box(contentAlignment = Alignment.BottomStart) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    backgroundColor = Color.Black,
                    text = { Text(text = stringResource(id = R.string.set_wallpaper), color = Color.White) },
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}
