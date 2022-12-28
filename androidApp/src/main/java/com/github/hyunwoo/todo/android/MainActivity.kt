package com.github.hyunwoo.todo.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.hyunwoo.todo.Greeting
import com.github.hyunwoo.todo.SpaceXSDK
import com.github.hyunwoo.todo.android.databinding.ActivityMainBinding
import com.github.hyunwoo.todo.shared.cache.space.DatabaseDriverFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = LaunchInfoAdapter(listOf())
    private val sdk = SpaceXSDK(DatabaseDriverFactory(this))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.launchesListRv.adapter = adapter
        binding.launchesListRv.layoutManager = LinearLayoutManager(this)

        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
            displayLaunches(true)
        }
        displayLaunches(false)
    }

    private fun displayLaunches(forceRefresh: Boolean) {
        binding.progressBar.isVisible = true
        lifecycleScope.launch {
            runCatching {
                sdk.getLaunches(forceRefresh)
            }.onSuccess {
                adapter.replaceList(it)
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
            binding.progressBar.isVisible = false
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
