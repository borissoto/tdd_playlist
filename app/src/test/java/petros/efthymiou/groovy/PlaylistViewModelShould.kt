package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.verify
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val viewModel: PlaylistViewModel
    val repository: PlaylistRepository = mock()

    init {

        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun getPlaylistsFromRepository() {
        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }
}