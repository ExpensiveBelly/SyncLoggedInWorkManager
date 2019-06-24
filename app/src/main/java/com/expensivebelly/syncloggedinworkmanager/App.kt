package com.expensivebelly.syncloggedinworkmanager

import android.app.Application
import android.content.Context
import com.expensivebelly.syncloggedinworkmanager.di.ApplicationComponent
import com.expensivebelly.syncloggedinworkmanager.di.ApplicationModule
import com.expensivebelly.syncloggedinworkmanager.di.DaggerApplicationComponent

class App : Application() {

    var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}

val Context.applicationComponent
    get() = (this as App).applicationComponent