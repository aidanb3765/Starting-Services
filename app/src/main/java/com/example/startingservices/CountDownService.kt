package com.example.startingservices

import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CountdownService : IntentService("CountdownService"), CoroutineScope {

    companion object {
        const val COUNTDOWN_VALUE = "countdown_value"
    }

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate() {
        super.onCreate()
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()  // Cancel the job when the service is destroyed
    }

    override fun onHandleIntent(intent: Intent?) {
        val countdownValue = intent?.getIntExtra(COUNTDOWN_VALUE, 0) ?: 0

        if (countdownValue > 0) {
            runBlocking {
                repeat(countdownValue) {
                    delay(1000) // Delay for 1 second
                    logCountdownMessage(countdownValue - it)
                }
            }
        }
    }

    private fun logCountdownMessage(countdownValue: Int) {
        val message = "Countdown: $countdownValue"
        Log.d("CountdownService", message)
    }
}
