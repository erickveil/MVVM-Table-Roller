package net.erickveil.mvvmtableroller.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DungeonLootTable (
    @SerialName("tableName") val tableName: String,
    @SerialName("description") val descriiption: String,
    @SerialName("results") val results: List<String>
)

