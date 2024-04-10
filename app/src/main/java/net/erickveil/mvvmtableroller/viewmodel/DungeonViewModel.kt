package net.erickveil.mvvmtableroller.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.erickveil.mvvmtableroller.model.DungeonLootRepository

class DungeonViewModel(private val repository: DungeonLootRepository): ViewModel() {
    // Using StateFlow to hold and emit dungeon loot content
    private val _dungeonContent = MutableStateFlow<String>("Press the button to generate loot!")
    val dungeonContent: StateFlow<String> = _dungeonContent

    fun generateDungeonLoot(context: Context) {
        viewModelScope.launch{
            // Load the dungeon loot table from the repository
            val lootTable = repository.loadDungeonLootTable()
            // Pick a random item from the results and update the StateFlowo
            lootTable?.results?.let {
                _dungeonContent.value = if (it.isNotEmpty())  it.random() else  "No loot found."
            }
        }

    }
}