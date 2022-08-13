package com.example.githubrepositories.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesAPIServiceTest {

    private lateinit var service: RepositoriesAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepositoriesAPIService::class.java)
    }

    private fun enqueueMockResponse(
      fileName:String
    ){
      val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)

    }

    @Test
    fun getRepositories_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("repositoriesresponse.json")
            val responseBody = service.getRepositories("kotlin").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/search/repositories?q=kotlin")
        }
    }

    @Test
    fun getRepositories_receivedResponse_correctPageSize(){
      runBlocking {
          enqueueMockResponse("repositoriesresponse.json")
          val responseBody = service.getRepositories("kotlin").body()
          val articlesList = responseBody!!.repositoryItems
          assertThat(articlesList.size).isEqualTo(20)
      }
    }

    @Test
    fun getRepositories_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("repositoriesresponse.json")
            val responseBody = service.getRepositories("kotlin").body()
            val repositoryList = responseBody!!.repositoryItems
            val repository = repositoryList[0]
            assertThat(repository.id).isEqualTo(3432266)
            assertThat(repository.fullName).isEqualTo("JetBrains/kotlin")
        }
    }

    @After
    fun tearDown() {
       server.shutdown()
    }
}