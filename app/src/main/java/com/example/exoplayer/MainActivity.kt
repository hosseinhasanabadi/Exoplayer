package com.example.exoplayer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import coil.compose.AsyncImage
import com.example.exoplayer.model.VideoList
import com.example.exoplayer.ui.theme.ExoPlayerTheme
import com.example.exoplayer.viewmodel.VideoPlayerViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExoPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    StreamingVideo()

                }
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun StreamingVideo() {
    //test mikone bebeineh in plyer kar mikone yani dar hal pakhshe ya na

    var isPlaying by remember {
        mutableStateOf(false)
    }
    //chek kardan index video dar hal pakhshe har bar click mire be view model viedo jadid paljsh mishe

    var videoItemIndex by remember {
        mutableStateOf(0)

    }
    val context = LocalContext.current
    val viewModel: VideoPlayerViewModel = viewModel()
    viewModel.videoList = VideoList
    Column(Modifier.fillMaxSize()) {
        ExoPlayer(viewModel = viewModel, isPlaying = isPlaying, onPlayerClosed = {isPlaying=it})


        LazyColumn(Modifier.padding(10.dp)) {
            itemsIndexed(items = VideoList) { index, item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (videoItemIndex!=index) isPlaying =false
                            viewModel.index = index
                            videoItemIndex = viewModel.index

                        },
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    AsyncImage(
                        model = item.thumbnail,
                        contentDescription = "video thumbnail",
                    )
                    Text(
                        text = "Video ${index + 1}", modifier = Modifier.weight(1f)
                    )

                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )


            }

        }
        LaunchedEffect(key1 = videoItemIndex) {
            isPlaying = true
            viewModel.releasePlayer()
            viewModel.initializePlayer(context)
            viewModel.playVideo()
            
        }


    }

}
