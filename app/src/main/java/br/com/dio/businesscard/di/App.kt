package br.com.dio.businesscard.di

import android.app.Application
import br.com.dio.businesscard.data.DatabaseHelper
import br.com.dio.businesscard.data.BusinessCardRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    private val database by lazy { DatabaseHelper.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }


}