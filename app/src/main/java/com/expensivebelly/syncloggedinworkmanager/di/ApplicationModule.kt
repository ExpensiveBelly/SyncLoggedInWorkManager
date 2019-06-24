package com.expensivebelly.syncloggedinworkmanager.di

import android.content.Context
import com.expensivebelly.syncloggedinworkmanager.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: App) {

    @Provides
    fun provideApplicationContext(): Context = application
}
