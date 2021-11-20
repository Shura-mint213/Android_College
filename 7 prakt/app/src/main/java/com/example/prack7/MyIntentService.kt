package com.example.prack7

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_DECOMPOSITION = "com.example.prack7.action.DECOMPOSITION"

// TODO: Rename parameters
private const val EXTRA_NUMBER = "com.example.prack7.extra.PARAM1"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_DECOMPOSITION -> {
                val param1 = intent.getStringExtra(EXTRA_NUMBER)
                handleActionDecomposition(param1)//todo Int
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionDecomposition(param1: String?) { //todo Int
        var number = 0
        if (param1!!.toIntOrNull() != null)
            number = param1!!.toInt()
            var count = 2
            var resultMultiplication = 1
            var resultString = ""
            while (resultMultiplication < number) {
                if (count % 2 != 0 || count % 3 != 0)
                    if (number % (resultMultiplication * count) == 0) {
                        resultString += " $count *"
                        resultMultiplication *= count
                        count = 1
                    }
                count++
            }
            val intent = Intent(ACTION_RESULT)

            intent.putExtra(RESULT,
                if (resultString == "") "" else resultString.drop(1).dropLast(2)
            )
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionDecomposition(context: Context, param1: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_DECOMPOSITION
                putExtra(EXTRA_NUMBER, param1)
            }
            context.startService(intent)
        }
        const val RESULT = "RESULT"
        const val ACTION_RESULT = "ACTION_RESULT"
    }
}