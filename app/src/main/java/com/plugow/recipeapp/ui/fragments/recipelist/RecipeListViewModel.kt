package com.plugow.recipeapp.ui.fragments.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plugow.recipeapp.data.RecipeItem
import com.plugow.recipeapp.data.api.RecipeDto
import com.plugow.recipeapp.data.map
import com.plugow.recipeapp.util.api.repo.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val recipeRepo: RecipeRepo) : ViewModel() {
    private val _recipes = MutableLiveData<RecipeListState>()
    val recipes: LiveData<RecipeListState> = _recipes

    init {
        viewModelScope.launch {
            _recipes.value = RecipeListState.Loading
            val result = withContext(Dispatchers.IO) {
                recipeRepo.getRecipeList(1, "").map {
                    it.map { it.toRecipeItem() }
                }
            }
            result.fold({
                _recipes.value = RecipeListState.Error
            }, {
                _recipes.value = RecipeListState.Success(it)
            })
        }
    }


}

private fun RecipeDto.toRecipeItem(): RecipeItem {
    return RecipeItem(pk, title, featuredImage, rating)
}
