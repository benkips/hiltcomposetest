package com.mabnets.hiltcomposetest.Utils.composescreens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.mabnets.hiltcomposetest.R
import com.mabnets.hiltcomposetest.presentation.Home.Newsviewmodel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun  NewsScreen(viewmodel: Newsviewmodel = hiltViewModel()) {
    val mContext = LocalContext.current
    val newsitems=viewmodel.state
    Log.d("Errormsg", "Fromapp:${newsitems.error}")
    LazyColumn{
        items(newsitems.NewsItems){
            ListItem {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            /*navController.navigate("${Destinations.DETAILS_SCREEN}/${new.title}")*/
                            Toast
                                .makeText(
                                    mContext,
                                    "clicked on image",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                ) {
                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = it.imagelink,
                                builder = {
                                    error(R.drawable.ic_error)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(it.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(it.description ?: "", maxLines = 3)
                        }
                    }

                }
            }
        }
    }
}