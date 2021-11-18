package com.github.zsoltk.composeribs.core.routing

import com.github.zsoltk.composeribs.core.plugin.UpNavigationHandler
import kotlinx.coroutines.flow.StateFlow

interface RoutingSource<Key, State> : UpNavigationHandler {

    val elements: StateFlow<RoutingElements<Key, out State>>

    val canHandleBackPress: StateFlow<Boolean>

    fun onBackPressed()

    fun onTransitionFinished(key: RoutingKey<Key>)

    /**
     * Bundle for future state restoration.
     * Result should be supported by [androidx.compose.runtime.saveable.SaverScope.canBeSaved].
     */
    fun saveInstanceState(savedStateMap: MutableMap<String, Any>) {}

    override fun handleUpNavigation(): Boolean =
        canHandleBackPress.value.also { if (it) onBackPressed() }

}
