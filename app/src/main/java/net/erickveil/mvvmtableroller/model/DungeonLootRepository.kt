package net.erickveil.mvvmtableroller.model

import android.content.Context
import android.util.Log
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.IOException

class DungeonLootRepository(private val context: Context) {

    fun getLootTable(): DungeonLootTable? {
        val jsonString = loadDungeonLootTable()
        return jsonString?.let { parseJsonToLootTable(it) }
    }
    private fun loadDungeonLootTable(): String? {
        return try {
            context.assets.open("lootTable.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    private fun parseJsonToLootTable(jsonString: String): DungeonLootTable? {
        return try {
            Json.decodeFromString(serializer(), jsonString)
        } catch (exception: SerializationException) {
            exception.printStackTrace()
            null
        }
    }
}