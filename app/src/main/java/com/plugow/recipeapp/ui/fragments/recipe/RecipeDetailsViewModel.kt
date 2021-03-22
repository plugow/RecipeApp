package com.plugow.recipeapp.ui.fragments.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plugow.recipeapp.data.RecipeId
import com.plugow.recipeapp.util.api.repo.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val recipeRepo: RecipeRepo) : ViewModel() {
    private val _recipeState = MutableLiveData<RecipeState>()
    val recipeState: LiveData<RecipeState> = _recipeState

    fun getRecipe(id: Int?) {
        val recipeId = if (id != null) RecipeId(id) else {
            _recipeState.value = RecipeState.Error("")
            return
        }

        viewModelScope.launch {
            _recipeState.value = RecipeState.Loading
            val recipe = withContext(Dispatchers.IO) {
                recipeRepo.getRecipe(recipeId)
            }
            recipe.fold({
                _recipeState.value = RecipeState.Error("")
            }, {
                _recipeState.value = RecipeState.Success(it)
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}