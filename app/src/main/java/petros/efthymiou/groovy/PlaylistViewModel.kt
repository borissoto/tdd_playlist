package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel(
    private val repository: PlaylistRepository
): ViewModel() {

    val playlists = MutableLiveData<Result<List<Playlist>>>()

    init{
        repository.getPlaylists()
    }

}
