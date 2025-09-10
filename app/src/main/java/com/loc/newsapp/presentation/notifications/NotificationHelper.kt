package com.loc.newsapp.presentation.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.loc.newsapp.R

object NotificationHelper {

    fun showBookmarkNotification(context: Context, title: String) {
        val notification = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.informa_scope_icon)
            .setContentTitle("Article Saved")
            .setContentText(title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
