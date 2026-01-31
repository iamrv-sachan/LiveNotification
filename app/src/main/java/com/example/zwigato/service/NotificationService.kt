package com.example.zwigato.service

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.zwigato.LiveNotificationManager
import com.example.zwigato.OrderProgress
import com.example.zwigato.OrderStatus
import com.example.zwigato.R

class NotificationService: Service() {

    companion object {
        private const val NOTIFICATION_ID = 4321
        private const val CHANNEL_ID = "live_order_update_id"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra("ORDER_DATA", OrderProgress::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra("ORDER_DATA")
        }

        val status = data?.orderStatus ?: OrderStatus.Confirmed
        val progress = data?.progress ?: 0

        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA) {
            LiveNotificationManager.updateNotification(status, progress)
        } else {
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_outline)
                .setContentTitle("Order: $status")
                .setContentText("Progress: $progress%")
                .setProgress(100, progress, false)
                .setOngoing(true)
                .setSilent(true)
                .build()
        }

        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(4321)
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? = null
}