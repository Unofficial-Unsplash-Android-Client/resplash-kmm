package ru.fursa.unsplash.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fursa.unsplash.android.ui.screen.collection.CollectionPhotosViewModel
import ru.fursa.unsplash.android.ui.screen.collections.CollectionViewModel
import ru.fursa.unsplash.android.ui.screen.home.HomeViewModel
import ru.fursa.unsplash.android.ui.screen.profile.ProfileViewModel
import ru.fursa.unsplash.android.ui.screen.search.SearchViewModel
import ru.fursa.unsplash.android.ui.screen.viewer.ViewerViewModel

val viewModelsModule = module {
    viewModel { CollectionViewModel(repository = get(), searchEventBus = get()) }
    viewModel { HomeViewModel(repository = get()) }
    viewModel { SearchViewModel(repository = get()) }
    viewModel { ProfileViewModel(repository = get()) }
    viewModel { CollectionPhotosViewModel(repository = get()) }
    viewModel {
        ViewerViewModel(
            imageRequest = get(),
            loader = get(),
            wallpaperManager = get(),
            repository = get()
        )
    }
}
