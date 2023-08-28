package ru.fursa.unsplash.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fursa.unsplash.android.ui.screen.collections.CollectionViewModel

val viewModelsModule = module {
    viewModel { CollectionViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
