package com.somnionocte.preferencesstore

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PreferencesStore")

fun <T> MimicMutableState(
    get: () -> T,
    set: (value: T) -> Unit
) = object : MutableState<T> {
    override var value: T
        get() = get()
        set(value) = set(value)

    override fun component1() = value

    override fun component2(): (T) -> Unit = { value = it }
}

open class PreferencesStore(
    private val appContext: Context,
    val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    inline fun <reified T : Enum<T>> propertyAsState(
        keyName: String,
        initialValue: Enum<T>
    ): MutableState<T> {
        val state = propertyAsState(keyName, initialValue.name)
        return MimicMutableState({ enumValueOf<T>(state.value) }) { it.name }
    }

    fun propertyAsState(keyName: String, initialValue: Double): MutableState<Double> {
        val state = MutableStateFlow(initialValue)

        val key = doublePreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: Boolean): MutableState<Boolean> {
        val state = MutableStateFlow(initialValue)

        val key = booleanPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: ByteArray): MutableState<ByteArray> {
        val state = MutableStateFlow(initialValue)

        val key = byteArrayPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: Set<String>): MutableState<Set<String>> {
        val state = MutableStateFlow(initialValue)

        val key = stringSetPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: Long): MutableState<Long> {
        val state = MutableStateFlow(initialValue)

        val key = longPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: Float): MutableState<Float> {
        val state = MutableStateFlow(initialValue)

        val key = floatPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: String): MutableState<String> {
        val state = MutableStateFlow(initialValue)

        val key = stringPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }

    fun propertyAsState(keyName: String, initialValue: Int): MutableState<Int> {
        val state = MutableStateFlow(initialValue)

        val key = intPreferencesKey(keyName)

        val property = appContext.dataStore.data.map { it[key] ?: initialValue }

        val mimicState = MimicMutableState({ state.value }) { value ->
            coroutineScope.launch {
                runCatching {
                    appContext.dataStore.edit { it[key] = value }
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }

        coroutineScope.launch {
            property.collectLatest { value -> state.update { value } }
        }

        return mimicState
    }
}