package com.arkangel.ulessontechnicaltest.android.utils

import android.content.Context
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.offline.DownloadService
import com.arkangel.ulessontechnicaltest.android.VideoDownloadService

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class DownloadManagerImpl(
    private val context: Context,

): DownloadManager {
    override fun queueDownload(downloadRequest: DownloadRequest) {
        DownloadService.sendAddDownload(
            context,
            VideoDownloadService::class.java,
            downloadRequest,
            false
        )
        DownloadService.sendSetStopReason(
            context,
            VideoDownloadService::class.java,
            downloadRequest.uri.toString(),
            Download.STOP_REASON_NONE,
            false
        )
    }

    override fun removeDownload(contentId: String) {
        DownloadService.sendRemoveDownload(context, VideoDownloadService::class.java, contentId, false)
    }

    override fun pauseAllDownloads() {
        DownloadService.sendPauseDownloads(context, VideoDownloadService::class.java, false)
    }

    override fun resumeAllDownloads() {
        DownloadService.sendResumeDownloads(context, VideoDownloadService::class.java, false)
    }
}