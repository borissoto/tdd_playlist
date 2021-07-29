package petros.efthymiou.groovy.details

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(){
    suspend fun fetchPlaylistDetails(id: String) : Flow<Result<PlaylistDetails>>{
        TODO("NOT YET IMPLEMENTED")
    }
}
