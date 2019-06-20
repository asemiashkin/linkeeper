package services

import com.asemiashkin.Link
import com.asemiashkin.persistence.LinksRepository
import com.asemiashkin.services.LinksService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Test
import java.net.URI
import java.util.*
import kotlin.random.Random

class LinksServiceTest {

    private val link = Link(0, "test", URI.create("/test"), 0)

    @Test
    fun testCreate() {
        val linksRepository = mock<LinksRepository> {
            on { create(any()) } doReturn Random.nextLong()
        }

        val linksService = LinksService(linksRepository)

        Assert.assertNotNull(linksService.create(link))
    }

    @Test
    fun testUpdate() {
        val linksRepository = mock<LinksRepository> {
            on { update(any()) } doReturn true
        }

        val linksService = LinksService(linksRepository)

        Assert.assertTrue(linksService.update(link))
    }

    @Test
    fun testRemove() {
        val linksRepository = mock<LinksRepository> {
            on { remove(any()) } doReturn true
        }

        val linksService = LinksService(linksRepository)

        Assert.assertTrue(linksService.remove(0))
    }

    @Test
    fun testFetch() {
        var link1 = link
        val linksRepository = mock<LinksRepository> {
            on { fetch(any()) } doReturn link1
        }

        val linksService = LinksService(linksRepository)

        Assert.assertEquals(linksService.fetch(0), link1)
    }

    @Test
    fun testFetchAll() {
        var link1 = link
        val singletonList = Collections.singletonList(link1)

        val linksRepository = mock<LinksRepository> {
            on { fetchAll() } doReturn singletonList
        }

        val linksService = LinksService(linksRepository)

        Assert.assertEquals(linksService.fetchAll(), singletonList)
    }
}