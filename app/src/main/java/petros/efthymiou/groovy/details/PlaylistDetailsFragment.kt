package petros.efthymiou.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist_detail.*
import petros.efthymiou.groovy.PlaylistDetailFragmentArgs
import petros.efthymiou.groovy.R
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    lateinit var viewModel: PlaylistDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    val args: PlaylistDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_playlist_detail, container, false)
        val id = args.playlistId

        setupViewModel()

        viewModel.getPlaylistDetails(id)

        observeLiveData()

        return view
    }

    private fun observeLiveData() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupUI(playlistDetails)
            } else {

            }
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlaylistDetailsViewModel::class.java)
    }

    private fun setupUI(playlistDetails: Result<PlaylistDetails>) {
        playlist_name.text = playlistDetails.getOrNull()!!.name
        playlist_details.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic fun newInstance() =
            PlaylistDetailsFragment()
    }
}