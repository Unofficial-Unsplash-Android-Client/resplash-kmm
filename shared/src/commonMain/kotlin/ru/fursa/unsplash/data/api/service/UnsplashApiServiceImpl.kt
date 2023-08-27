package ru.fursa.unsplash.data.api.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import ru.fursa.unsplash.data.api.models.base.User
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.api.models.search.SearchCollectionResponse
import ru.fursa.unsplash.data.api.models.search.SearchPhotoResponse
import ru.fursa.unsplash.data.api.models.search.UserSearchResponse
import ru.fursa.unsplash.domain.base.UnsplashApiService

internal class UnsplashApiServiceImpl constructor(private val httpClient: HttpClient): UnsplashApiService {

    override suspend fun getCollections(pageIndex: Int, perPage: Int): List<CollectionResponse> {
        return httpClient.get {
            url {
                appendPathSegments("/collections")
                method = HttpMethod.Get
                parameters.append("page", pageIndex.toString())
                parameters.append("per_page", perPage.toString())
            }
        }.body()
    }

    override suspend fun getPhotos(pageIndex: Int, perPage: Int): List<PhotoResponse> {
        return httpClient.get {
            url {
                appendPathSegments("/photos")
                method = HttpMethod.Get
                parameters.append("page", pageIndex.toString())
                parameters.append("per_page", perPage.toString())
            }
        }.body()
    }

    override suspend fun searchPhotos(query: String, pageIndex: Int, perPage: Int): SearchPhotoResponse {
        return httpClient.get {
            url {
                appendPathSegments("/search/photos")
                method = HttpMethod.Get
                parameters.append("page", pageIndex.toString())
                parameters.append("per_page", perPage.toString())
                parameters.append("query", query)
            }
        }.body()
    }

    override suspend fun searchCollections(
        query: String,
        pageIndex: Int,
        perPage: Int
    ): SearchCollectionResponse {
        return httpClient.get {
            url {
                appendPathSegments("/search/collections")
                method = HttpMethod.Get
                parameters.append("page", pageIndex.toString())
                parameters.append("per_page", perPage.toString())
                parameters.append("query", query)
            }
        }.body()
    }
    override suspend fun searchUsers(
        query: String,
        pageIndex: Int,
        perPage: Int
    ): UserSearchResponse {
        return httpClient.get {
            url {
                appendPathSegments("/search/users")
                method = HttpMethod.Get
                parameters.append("page", pageIndex.toString())
                parameters.append("per_page", perPage.toString())
                parameters.append("query", query)
            }
        }.body()
    }

    override suspend fun getUser(username: String): User {
        return httpClient.get {
            url {
                appendPathSegments("/users/$username")
                method = HttpMethod.Get
            }
        }.body()
    }

    override suspend fun getUserPhotos(
        username: String,
        pageIndex: Int,
        perPage: Int
    ): List<PhotoResponse> {
        return httpClient.get {
            url {
                appendPathSegments("/users/$username/photos")
            }
        }.body()
    }

    override suspend fun getUserLikes(
        username: String,
        pageIndex: Int,
        perPage: Int
    ): List<PhotoResponse> {
        return httpClient.get {
            url {
                appendPathSegments("/users/$username/likes")
            }
        }.body()
    }

    override suspend fun getUserCollections(
        username: String,
        pageIndex: Int,
        perPage: Int
    ): List<CollectionResponse> {
        return httpClient.get {
            url {
                appendPathSegments("/users/$username/collections")
            }
        }.body()
    }
}