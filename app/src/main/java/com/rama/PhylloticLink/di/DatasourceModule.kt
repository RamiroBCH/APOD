
package com.rama.PhylloticLink.di

import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.domain.Datasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindDatasource(datasourceImpl: DatasourceImpl): Datasource
}

