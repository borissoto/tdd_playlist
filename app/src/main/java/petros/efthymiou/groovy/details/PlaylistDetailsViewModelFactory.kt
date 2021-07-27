package petros.efthymiou.groovy.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import petros.efthymiou.groovy.playlist.PlaylistRepository
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistDetailsViewModel() as T
    }


}
