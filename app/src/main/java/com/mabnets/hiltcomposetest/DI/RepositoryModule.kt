package com.mabnets.hiltcomposetest.DI

import com.mabnets.hiltcomposetest.data.repositories.Localnewsimpl
import com.mabnets.hiltcomposetest.data.repositories.Newsrepoimpl
import com.mabnets.hiltcomposetest.domain.repositories.LocalRepo
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindNewsRepository(impl: Newsrepoimpl): NewsRepo

    @Binds
    abstract fun bindlocalNewsRepository(impl: Localnewsimpl):LocalRepo

}