package com.tapan.aetherai.data.audio

import android.Manifest
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs

class AudioRecorder {

    private val _amplitude = MutableStateFlow(0f)

    val amplitude: StateFlow<Float> = _amplitude

    private var recorder: AudioRecord? = null

    private var isRecording = false


    fun startListening() {

        val bufferSize = AudioRecord.getMinBufferSize(
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        recorder = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )

        recorder?.startRecording()

        isRecording = true

        val buffer = ShortArray(bufferSize)

        Thread {

            while (isRecording) {

                val read =
                    recorder?.read(
                        buffer,
                        0,
                        buffer.size
                    ) ?: 0

                var max = 0

                for (i in 0 until read) {

                    max = maxOf(
                        max,
                        abs(buffer[i].toInt())
                    )
                }

                _amplitude.value =
                    (max / 32767f)
                        .coerceIn(0f, 1f)
            }
        }.start()
    }

    fun stopListening() {

        isRecording = false

        recorder?.stop()

        recorder?.release()

        recorder = null

        _amplitude.value = 0f
    }
}