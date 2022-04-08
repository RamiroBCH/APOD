package com.rama.PhylloticLink.di

import com.rama.PhylloticLink.domain.Repo
import com.rama.PhylloticLink.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindRepo(repoImpl: RepoImpl): Repo
}
