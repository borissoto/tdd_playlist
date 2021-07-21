package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()

    @Test
    fun fetchPlaylistsFromAPI() = runBlockingTest {
        service = PlaylistService(api)

        service.fetchPlaylists()

        verify(api, times(1)).fetchAllPlaylists()
    }


    @Test
    fun convertValuesToFlowResultAndEmitsThen() = runBlockingTest{

    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest{

    }
}