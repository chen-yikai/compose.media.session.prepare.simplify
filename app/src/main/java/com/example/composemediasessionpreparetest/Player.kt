package com.example.myapp

import android.content.ComponentName
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.composemediasessionpreparetest.PlaybackService
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Composable
fun MediaPlayerScreen() {
    val context = LocalContext.current
    var player by remember { mutableStateOf<Player?>(null) }
    val isPlaying by PlaybackService.isPlaying.collectAsState()
    val audioUrl = "https://skills-music-api-v2.eliaschen.dev/audio/ocean.mp3"

    Column {
        Text(
            text = when {
                player?.isPlaying == true -> "Playing Ocean Audio"
                player?.isLoading == true -> "Loading Ocean Audio"
                else -> "Stopped"
            }
        )
        Text(if (isPlaying) "t" else "f")
        Button(
            onClick = {
                PlaybackService.playAudio(audioUrl)
            },
        ) {
            Text("Play Ocean Audio")
        }
        Button(
            onClick = { PlaybackService.playerInstance?.pause() },
        ) {
            Text("Pause")
        }
    }
}