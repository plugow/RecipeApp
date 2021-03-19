package com.plugow.recipeapp.util.di

import com.plugow.recipeapp.util.api.repo.RecipeRepo
import com.plugow.recipeapp.util.api.repo.RecipeRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    @Reusable
    abstract fun bindRecipeRepo(recipeRepoImpl: RecipeRepoImpl): RecipeRepo
}