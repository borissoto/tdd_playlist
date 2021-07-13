package petros.efthymiou.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaylistViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel() as T
    }

}
