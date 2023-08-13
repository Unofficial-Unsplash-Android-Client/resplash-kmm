package ru.fursa.unsplash.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fursa.unsplash.android.ui.screen.collections.CollectionViewModel
import ru.fursa.unsplash.android.ui.screen.home.HomeViewModel

val viewModelsModule = module {
    viewModel { CollectionViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}