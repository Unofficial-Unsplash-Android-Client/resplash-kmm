package ru.fursa.unsplash.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

actual class UnsplashApiService actual constructor(val httpClient: HttpClient){

    actual suspend fun getCollections(pageIndex: Int): List<CollectionHttpResponse> {
            return httpClient.get {
                url {
                    appendPathSegments(BaseApi.COLLECTIONS_REQ)
                    method = HttpMethod.Get
                    parameters.append(BaseApi.PAGE_INDEX_PARAM, pageIndex.toString())
                    parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
                }
            }.body()
    }

}