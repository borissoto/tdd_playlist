package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.verify
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository()  = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues{
            viewModel.playlists.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistsLoad() = runBlockingTest{
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderAfterError() = runBlockingTest {
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()

            assertEquals(false,values.last())
        }
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }
        return PlaylistViewModel(repository)
    }

}