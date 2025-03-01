package com.kiparo.wildcatalog.presentation.catalog.delegates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.databinding.ItemCatalogVideoBinding
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import com.kiparo.wildcatalog.presentation.catalog.model.VideoItem

class VideoStreamDelegateAdapter(
    private val generateNextVideoListener: (video: VideoItem) -> Unit
) : CatalogItemDelegateAdapter<VideoItem, VideoStreamDelegateAdapter.ViewHolder> {

    private var context: Context? = null

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            ItemCatalogVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            generateNextVideoListener = generateNextVideoListener
        )
    }

    override fun bindViewHolder(
        item: VideoItem,
        viewHolder: ViewHolder
    ) {
        viewHolder.bind(item)
    }

    override fun bindViewHolder(
        item: VideoItem,
        viewHolder: ViewHolder,
        payloads: List<CatalogItem.PayloadChange>
    ) {
        viewHolder.bind(item)
    }

    override val itemClass = VideoItem::class.java

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        context = null
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        (holder as Playback).play()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        (holder as Playback).pause()
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as Playback).release()
    }


    class ViewHolder(
        private val binding: ItemCatalogVideoBinding,
        private val generateNextVideoListener: (video: VideoItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), Playback {

        private var videoPlayer: ExoPlayer? = null

        fun bind(video: VideoItem) {
            binding.txtVideoTitle.text = video.title
            binding.txtVideoDescription.text = video.description

            binding.btnAddNext.setOnClickListener {
                generateNextVideoListener(video)
            }

            if (videoPlayer == null) {
                videoPlayer = initPlayer()
                binding.videoView.player = videoPlayer
            }
            videoPlayer?.clearMediaItems()
            videoPlayer?.setMediaItem(MediaItem.fromUri(video.videoUri))
            prepare()
        }

        override fun play() {
            if (videoPlayer?.isPlaying == false) {
                videoPlayer?.play()
            }
        }

        override fun pause() {
            if (videoPlayer?.isPlaying == true) {
                videoPlayer?.pause()
            }
        }

        override fun stop() {
            if (videoPlayer?.isPlaying == true) {
                videoPlayer?.stop()
            }
        }

        override fun prepare() {
            if (videoPlayer?.isLoading == false) {
                videoPlayer?.prepare()
            }
        }

        override fun release() {
            if (videoPlayer?.isPlaying == true) {
                videoPlayer?.stop()
                videoPlayer?.release()
                videoPlayer = null
                binding.videoView.player = null
            }
        }

        private fun initPlayer() = ExoPlayer.Builder(binding.root.context)
            .build()
            .also { exoPlayer ->
                exoPlayer.playWhenReady = false
                exoPlayer.addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        super.onPlayerError(error)
                    }
                })
            }
    }

    interface Playback {
        fun play()
        fun pause()
        fun stop()
        fun prepare()
        fun release()
    }
}
