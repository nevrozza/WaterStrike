package main

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import main.MainStore.*

class MainStoreFactory(
    private val storeFactory: StoreFactory,
    val executor: MainExecutor
) {
    fun create(): MainStore {
        return MainStoreImpl()
    }
    private inner class MainStoreImpl :
            MainStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "MainStore",
            initialState = State(),
            executorFactory = ::executor,
            reducer = MainReducer,
            bootstrapper = SimpleBootstrapper(Unit)
        )
}