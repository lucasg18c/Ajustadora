package com.slavik.aproximadorfunciones.di

import android.app.Application
import androidx.room.Room
import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import com.slavik.aproximadorfunciones.mmc.datos.converter.FechaConverter
import com.slavik.aproximadorfunciones.mmc.datos.converter.ListaPuntosConverter
import com.slavik.aproximadorfunciones.mmc.dominio.casos_uso.CasosUso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuloApp {

    @Provides
    @Singleton
    fun provideDB(app: Application) : AjustadoraDB {
        return Room.databaseBuilder(
            app,
            AjustadoraDB::class.java,
            "ajustadora_db"
        )
            .addTypeConverter(ListaPuntosConverter())
            .addTypeConverter(FechaConverter())
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositorio(db: AjustadoraDB) : Repositorio {
        return Repositorio(db)
    }

    @Provides
    @Singleton
    fun provideCasosUso(db: AjustadoraDB) : CasosUso {
        return CasosUso(db)
    }
}