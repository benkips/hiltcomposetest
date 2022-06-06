package com.mabnets.hiltcomposetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hiltcomposetest.VerticalNestedScrollView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.mabnets.hiltcomposetest.Utils.composescreens.NewsScreen
import com.mabnets.hiltcomposetest.ui.theme.NestedscrollviewTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedscrollviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Nestedscrollview()
                }
            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun Nestedscrollview() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text ="TestApp",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        val scope = rememberCoroutineScope()
        val nestedScrollViewState = rememberNestedScrollViewState()
        VerticalNestedScrollView(state = nestedScrollViewState,
            header = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.primary
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(text = "Make it easy")
                        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas pulvinar quam quis diam tempus,")
                    }

                }
            },
            content = {
                val pagestate = rememberPagerState(pageCount = 10)
                val newsfeatures=arrayOf<String>("tuko","kenyans","The star")
                val pages = (newsfeatures).map { it }
                val mContext = LocalContext.current


                Column {
                    TabRow(selectedTabIndex = pagestate.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.pagerTabIndicatorOffset(pagestate, tabPositions)
                            )
                        }
                    ) {
                        pages.forEachIndexed{
                                index, title ->
                            Tab(
                                text = { Text(text = "${title}") },
                                selected = pagestate.currentPage == index,
                                onClick = {

                                    Toast.makeText(mContext, "${title}", Toast.LENGTH_SHORT).show()
                                    scope.launch {
                                        pagestate.animateScrollToPage(index)
                                    }
                                },
                            )
                        }

                    }
                    HorizontalPager(
                        modifier = Modifier.weight(1f),
                        state = pagestate
                    ) {
                        NewsScreen()
                    }
                }


            })
    }
}

