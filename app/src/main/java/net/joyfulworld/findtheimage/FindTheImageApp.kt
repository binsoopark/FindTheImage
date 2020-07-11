package net.joyfulworld.findtheimage

import android.app.Application
import net.joyfulworld.base.di.baseNetworkKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FindTheImageApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FindTheImageApp)
            androidFileProperties()

            modules(baseNetworkKoinModule)
        }
    }
}