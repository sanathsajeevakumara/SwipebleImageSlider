package com.sanathcoding.swipebleimageslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanathcoding.swipebleimageslider.ui.theme.SwipebleImageSliderTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animalList = listOf(
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.chicken,
        )

        setContent {
            val pagerState = rememberPagerState()
            val scope = rememberCoroutineScope()
            SwipebleImageSliderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        HorizontalPager(
                            pageCount = animalList.size,
                            state = pagerState,
                            key = { animalList[it] },
                        ) { index ->
                            Image(
                                painter = painterResource(id = animalList[index]),
                                contentDescription = "Animal Images",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        // HorizontalPager Controllers
                        Box(
                            modifier = Modifier
                                .offset(y = -(16.dp))
                                .fillMaxWidth(0.5f)
                                .clip(RoundedCornerShape(100.dp))
                                .background(MaterialTheme.colors.background)
                                .padding(8.dp)
                                .align(Alignment.BottomCenter),
                        ) {
                            IconButton(
                                onClick = {
                                          scope.launch {
                                              pagerState.animateScrollToPage(
                                                  pagerState.currentPage - 1
                                              )
                                          }
                                },
                                modifier = Modifier.align(Alignment.CenterStart)
                            ) {
                                Icon(
                                   imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "Go Back"
                                )
                            }
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            pagerState.currentPage + 1
                                        )
                                    }
                                },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Go Forward"
                                )
                            }
                        }

                        // VerticalPager controllers
//                        Box(
//                            modifier = Modifier
//                                .offset(x = -(16.dp))
//                                .fillMaxHeight(0.2f)
//                                .clip(RoundedCornerShape(100.dp))
//                                .background(MaterialTheme.colors.background)
//                                .padding(8.dp)
//                                .align(Alignment.CenterEnd),
//                        ) {
//                            IconButton(
//                                onClick = {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(
//                                            pagerState.currentPage - 1
//                                        )
//                                    }
//                                },
//                                modifier = Modifier.align(Alignment.TopStart)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.KeyboardArrowUp,
//                                    contentDescription = "Go Back"
//                                )
//                            }
//                            IconButton(
//                                onClick = {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(
//                                            pagerState.currentPage + 1
//                                        )
//                                    }
//                                },
//                                modifier = Modifier.align(Alignment.BottomEnd)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.KeyboardArrowDown,
//                                    contentDescription = "Go Forward"
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}