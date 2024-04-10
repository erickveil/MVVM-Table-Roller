package net.erickveil.mvvmtableroller.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.erickveil.mvvmtableroller.model.DungeonLootRepository

class DungeonViewModelFactory(private val repository: DungeonLootRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DungeonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DungeonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
