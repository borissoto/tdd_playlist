package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach


class PlaylistViewModel(
    private val repository: PlaylistRepository
): ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<Playlist>>> {
        loader.postValue(true)

        emitSource(repository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }

}
