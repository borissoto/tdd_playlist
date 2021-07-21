package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val api: PlaylistAPI
) {
    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        api.fetchAllPlaylists()
        return flow {

        }
    }
}
