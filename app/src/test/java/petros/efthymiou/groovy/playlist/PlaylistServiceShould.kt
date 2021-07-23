package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<PlaylistRaw> = mock()

    @Test
    fun fetchPlaylistsFromAPI() = runBlockingTest {
        service = PlaylistService(api)

        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }


    @Test
    fun convertValuesToFlowResultAndEmitsThen() = runBlockingTest{
        mockSuccessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }


    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest{
        mockErrorCase()

        assertEquals("Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message)

    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developer"))

        service = PlaylistService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }
}