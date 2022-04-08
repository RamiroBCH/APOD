
package com.rama.PhylloticLink.di

import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.domain.Datasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindDatasource(datasourceImpl: DatasourceImpl): Datasource
}
