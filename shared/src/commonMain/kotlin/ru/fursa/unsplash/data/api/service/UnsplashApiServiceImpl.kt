package ru.fursa.unsplash.data.api.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import ru.fursa.unsplash.base.BaseApi
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.domain.base.UnsplashApiService

internal class UnsplashApiServiceImpl constructor(private val httpClient: HttpClient): UnsplashApiService {

    override suspend fun getCollections(pageIndex: Int, perPage: Int): List<CollectionResponse> {
        return httpClient.get {
            url {
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
                appendPathSegments(BaseApi.PHOTOS_REQ)
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                parameters.append(BaseApi.PER_PAGE_PARAM, perPage.toString())
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
            }
        }.body()
    }
}