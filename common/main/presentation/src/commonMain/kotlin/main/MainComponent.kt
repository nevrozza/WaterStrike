package main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import decompose.DefaultMVIComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import main.MainStore.Intent
import main.MainStore.Label
import main.MainStore.State
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: (Output) -> Unit
) : KoinComponent, ComponentContext by componentContext, DefaultMVIComponent<Intent, State, Label> {
    private val mainExecutor: MainExecutor by inject()

    private val factory = MainStoreFactory(
        storeFactory = storeFactory,
        executor = mainExecutor
    )

    override val store: Store<Intent, State, Label>
        get() = instanceKeeper.getStore {
            factory.create()
        }

    fun onOutput(output: Output) {
        output(output)
    }

    sealed class Output {
        data object NavigateToMatchList : Output()
    }
}