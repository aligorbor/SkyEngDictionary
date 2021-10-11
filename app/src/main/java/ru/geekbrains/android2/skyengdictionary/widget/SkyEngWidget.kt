package ru.geekbrains.android2.skyengdictionary.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.android2.skyengdictionary.R
import ru.geekbrains.android2.skyengdictionary.widget.remote.DoggyApi
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val MY_ACTION = "MY_ACTION"

class SkyEngWidget : AppWidgetProvider() {
    private val doggyApi: DoggyApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DoggyApi::class.java)
    }
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action != MY_ACTION) return
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidComponentName = ComponentName(context!!, SkyEngWidget::class.java)
        createRemoteViews(appWidComponentName, appWidgetManager, context)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val pendingIntent = Intent(context, SkyEngWidget::class.java).let { intent ->
            intent.action = MY_ACTION
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        val appWidComponentName = ComponentName(context!!, SkyEngWidget::class.java)
        createRemoteViews(appWidComponentName, appWidgetManager, context)
            .apply { setOnClickPendingIntent(R.id.doggy_image, pendingIntent) }
    }

    private fun createRemoteViews(
        appWidComponentName: ComponentName,
        appWidgetManager: AppWidgetManager?,
        context: Context?
    ): RemoteViews {
        return RemoteViews(
            context?.packageName,
            R.layout.skyeng_appwidget
        ).apply {
            scope.launch {
                val url = doggyApi.getRandomDog().message
                val bitmap = getBitmapFromUrl(url)
                setImageViewBitmap(R.id.doggy_image, bitmap)
                appWidgetManager?.updateAppWidget(appWidComponentName, this@apply)
            }
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    private fun getBitmapFromUrl(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }
    }
}