package com.aispeech.validationtv.service

import android.media.session.MediaSessionManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // 获取媒体会话
        val mediaSessionManager = getSystemService(MEDIA_SESSION_SERVICE) as MediaSessionManager
        val controllers = mediaSessionManager.getActiveSessions(null)
        for (controller in controllers) {
            // 打印出媒体会话信息
            val packageName = controller.packageName
            println("Active MediaController package: $packageName")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // 可选：处理通知移除事件
    }
}