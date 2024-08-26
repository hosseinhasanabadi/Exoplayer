package com.example.exoplayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.exoplayer.viewmodel.VideoPlayerViewModel

@Composable
fun ExoPlayer(viewModel:VideoPlayerViewModel
,isPlaying:Boolean,
              onPlayerClosed:(isVideoPLaying:Boolean)->Unit){
    Box (modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(2f),
        contentAlignment = Alignment.Center
    ){
        if (isPlaying){
            AndroidView(factory = {cont->
                viewModel.playerViewBuilder(context= cont)
            })
            IconButton(onClick = {
                                 onPlayerClosed(false)
                viewModel.releasePlayer()

            }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "",
                    tint = Color.White
                )

            }
        }
        else {
            Image(painter = painterResource(id = R.drawable.loading), contentDescription ="" )
        }

    }

}