package net.erickveil.mvvmtableroller.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.erickveil.mvvmtableroller.model.DungeonLootRepository
import net.erickveil.mvvmtableroller.model.DungeonLootTable

class DungeonViewModel(private val repository: DungeonLootRepository): ViewModel() {
    // Using StateFlow to hold and emit dungeon loot content
    private val _dungeonContent = MutableStateFlow<String>("Press the button to generate loot!")
    val dungeonContent: StateFlow<String> = _dungeonContent

    private var lootTable: DungeonLootTable? = null

    init {
        lootTable = repository.getLootTable()
    }

    fun generateDungeonLoot() {
        // Load the dungeon loot table from the repository
        // Pick a random item from the results and update the StateFlowo
        lootTable?.results?.let {
            _dungeonContent.value = if (it.isNotEmpty())  it.random() else  "No loot found."
        }

    }
}