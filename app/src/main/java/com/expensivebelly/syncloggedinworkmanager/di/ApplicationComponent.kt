package com.expensivebelly.syncloggedinworkmanager.di

import android.content.Context
import com.expensivebelly.syncloggedinworkmanager.data.LoginRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun applicationContext(): Context
    fun loginRepository(): LoginRepository
}