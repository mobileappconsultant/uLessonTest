package com.arkangel.ulessontechnicaltest.android

import android.app.Notification
import android.content.Context
import androidx.media3.common.util.NotificationUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadNotificationHelper
import androidx.media3.exoplayer.offline.DownloadService
import androidx.media3.exoplayer.scheduler.PlatformScheduler
import androidx.media3.exoplayer.scheduler.Scheduler
import com.arkangel.ulessontechnicaltest.android.utils.PlayerUtil
import org.koin.android.ext.android.inject
import java.lang.Exception

@UnstableApi class VideoDownloadService : DownloadService(
    FOREGROUND_NOTIFICATION_ID,
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    DOWNLOAD_NOTIFICATION_CHANNEL_ID,
    R.string.download_notification,
    0
) {

    private val playerUtil by inject<PlayerUtil>()

    companion object {
        const val JOB_ID = 1
        const val FOREGROUND_NOTIFICATION_ID = 1
        const val DOWNLOAD_NOTIFICATION_CHANNEL_ID = "download_notification"
    }

    override fun getDownloadManager(): DownloadManager {
        return playerUtil.getDownloadManager(applicationContext).apply {
            val downloadNotificationHelper = playerUtil.getDownloadNotificationHelper(applicationContext)
            addListener(TerminalStateNotificationHelper(this@VideoDownloadService, downloadNotificationHelper, FOREGROUND_NOTIFICATION_ID + 1))
        }
    }

    override fun getScheduler(): Scheduler? {
        return if (Util.SDK_INT >= 21) PlatformScheduler(this, JOB_ID) else null
    }

    override fun getForegroundNotification(
        downloads: MutableList<Download>,
        notMetRequirements: Int
    ): Notification {
        return playerUtil.getDownloadNotificationHelper(applicationContext)
            .buildProgressNotification(
                this, android.R.drawable.stat_sys_download_done,
                null, null, downloads, notMetRequirements
            )
    }
}

@UnstableApi private class TerminalStateNotificationHelper(
    private val context: Context,
    private val notificationHelper: DownloadNotificationHelper,
    private var nextNotificationId: Int
): DownloadManager.Listener {
    override fun onDownloadChanged(
        downloadManager: DownloadManager,
        download: Download,
        finalException: Exception?
    ) {
        val notification: Notification = when (download.state) {
            Download.STATE_COMPLETED -> {
                notificationHelper.buildDownloadCompletedNotification(context.applicationContext, android.R.drawable.stat_sys_download_done, null, Util.fromUtf8Bytes(download.request.data))
            }
            Download.STATE_FAILED -> {
                notificationHelper.buildDownloadFailedNotification(context, android.R.drawable.stat_sys_download_done, null, Util.fromUtf8Bytes(download.request.data))
            }
            else -> {
                return
            }
        }
        NotificationUtil.setNotification(context, nextNotificationId++, notification)
    }
}