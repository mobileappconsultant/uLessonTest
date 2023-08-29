package com.arkangel.ulessontechnicaltest.android.utils

import androidx.media3.exoplayer.offline.DownloadRequest

interface DownloadManager {
    fun queueDownload(downloadRequest: DownloadRequest)
    fun removeDownload(contentId: String)
    fun pauseAllDownloads()
    fun resumeAllDownloads()
}