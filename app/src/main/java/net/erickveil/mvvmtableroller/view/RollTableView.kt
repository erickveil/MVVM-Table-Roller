package net.erickveil.mvvmtableroller.view

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import net.erickveil.mvvmtableroller.model.DungeonLootRepository
import net.erickveil.mvvmtableroller.viewmodel.DungeonViewModel
import net.erickveil.mvvmtableroller.viewmodel.DungeonViewModelFactory

@Composable
fun DungeonGeneratorScreen(context: Context) {

    val repository = DungeonLootRepository(context)
    val viewModel: DungeonViewModel = viewModel(factory = DungeonViewModelFactory(repository))

    // Collecting the StateFlow as a state
    val dungeonContent = viewModel.dungeonContent.collectAsState()

    // Using MaterialTheme for light and dark themes support
    MaterialTheme (
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    )
    {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Output Text Container with Rounded Corners
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .heightIn(min = 200.dp),
                    shape = RoundedCornerShape(8.dp),
                    // Lighter shade for the text background
                    color = MaterialTheme.colorScheme.surface.copy(red = 0.5f, blue = 0.2f, alpha = 0.3f)
                ) {
                    Text(
                        text = dungeonContent.value,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Button with Rounded Corners
                Button(
                    onClick = {
                              viewModel.generateDungeonLoot()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Generate Dungeon Loot")
                }
            }
        }
    }
}