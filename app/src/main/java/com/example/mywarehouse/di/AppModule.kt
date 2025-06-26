package com.example.mywarehouse.di

import android.app.Application
import androidx.room.Room
import com.example.mywarehouse.data.local.InventoryDatabase
import com.example.mywarehouse.data.local.dao.InventoryDao
import com.example.mywarehouse.data.repository.InventoryRepository
import com.example.mywarehouse.data.repository.InventoryRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): InventoryDatabase {
        return Room.databaseBuilder(
            app,
            InventoryDatabase::class.java,
            "my_warehouse_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideInventoryDao(db: InventoryDatabase): InventoryDao {
        return db.inventoryDao()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

}
