package net.erickveil.mvvmtableroller.model

import android.content.Context
import kotlinx.serialization.json.Json

class DungeonLootRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadDungeonLootTable(): DungeonLootTable? {
        return try {
            context.assets.open("dungeon_loot_table.json").use { inputStream ->
                val jsonStr = inputStream.bufferedReader().use { it.readText() }
                json.decodeFromString<DungeonLootTable>(jsonStr)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}