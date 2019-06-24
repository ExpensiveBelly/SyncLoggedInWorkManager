package com.expensivebelly.syncloggedinworkmanager.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.expensivebelly.syncloggedinworkmanager.applicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class UploadWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result {
        // Do the work here--in this case, upload the images.
        if (applicationContext.applicationComponent?.loginRepository()?.isLoggedIn == true) {
            delay(5000)
            println("Uploading images...")
        }

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }
}