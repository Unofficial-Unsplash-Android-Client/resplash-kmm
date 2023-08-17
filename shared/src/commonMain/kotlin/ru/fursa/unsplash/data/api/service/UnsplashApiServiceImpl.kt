package ru.fursa.unsplash.data.api.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import ru.fursa.unsplash.base.BaseApi
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
                header("Accept-Version", "v1")
                header("Authorization", "Client-ID ${BaseApi.UNSPLASH_API_TOKEN}")
                appendPathSegments(BaseApi.COLLECTIONS_REQ)
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
            }
        }.body()
    }

    override suspend fun getPhotos(pageIndex: Int, perPage: Int): List<PhotoResponse> {
        return httpClient.get {
            url {
                header("Accept-Version", "v1")
                header("Authorization", "Client-ID ${BaseApi.UNSPLASH_API_TOKEN}")
                appendPathSegments(BaseApi.PHOTOS_REQ)
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
            }
        }.body()
    }

    override suspend fun searchPhotos(query: String, pageIndex: Int, perPage: Int): SearchPhotoResponse {
        return httpClient.get {
            url {
                header("Accept-Version", "v1")
                header("Authorization", "Client-ID ${BaseApi.UNSPLASH_API_TOKEN}")
                appendPathSegments(BaseApi.PHOTOS_SEARCH_REQ)
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.QUERY, query)
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
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
                header("Accept-Version", "v1")
                header("Authorization", "Client-ID ${BaseApi.UNSPLASH_API_TOKEN}")
                appendPathSegments("/search/collections")
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.QUERY, query)
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
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
                header("Accept-Version", "v1")
                appendPathSegments("/search/users")
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
                parameters.append(BaseApi.QUERY, query)
            }
        }.body()
    }
}