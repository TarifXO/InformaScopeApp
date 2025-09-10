package com.loc.newsapp.domain.useCases.news

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.loc.newsapp.data.repository.FakeNewsRepository
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNewsTest {

    private lateinit var getNews: GetNews
    private lateinit var fakeRepository: FakeNewsRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNewsRepository()
        getNews = GetNews(fakeRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getNews should return articles from repository`() = runTest {
        // Arrange
        val source1 = Source(id = "site1", name = "Site1 Name")
        val article1 = Article(
            author = "author1",
            content = "content1",
            description = "description1",
            publishedAt = "2024-06-01",
            source = source1,
            title = "Sample Title",
            url = "https://example.com/article1",
            urlToImage = "https://example.com/image1.jpg"
        )
        val source2 = Source(id = "site2", name = "Site2 Name")
        val article2 = Article(
            author = "author2",
            content = "content2",
            description = "description2",
            publishedAt = "2024-06-02",
            source = source2,
            title = "Sample Title 2",
            url = "https://example.com/article2",
            urlToImage = "https://example.com/image2.jpg"
        )

        fakeRepository.upsertArticle(article1)
        fakeRepository.upsertArticle(article2)

        // Act - get the first emitted PagingData from the use case
        val pagingData: PagingData<Article> = getNews(listOf("any_source")).first()

        // Use AsyncPagingDataDiffer to consume PagingData in a unit-test-friendly way
        val differ = AsyncPagingDataDiffer(
            diffCallback = Article.DIFF_CALLBACK,
            updateCallback = NoopListCallback(),
            mainDispatcher = UnconfinedTestDispatcher(),
            workerDispatcher = UnconfinedTestDispatcher()
        )

        // submit data and wait for background work to complete
        differ.submitData(pagingData)
        advanceUntilIdle()

        // Assert
        assertTrue(differ.itemCount > 0)
        assertEquals(2, differ.itemCount)
        assertEquals(article1, differ.snapshot()[0])
        assertEquals(article2, differ.snapshot()[1])
    }
}

/** No-op callback for AsyncPagingDataDiffer (test helper) */
class NoopListCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}