package com.example.juanmartinezleonitunes.Fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.juanmartinezleonitunes.Adapter.SongAdapter
import com.example.juanmartinezleonitunes.Model.SongResponse
import com.example.juanmartinezleonitunes.R
import com.example.juanmartinezleonitunes.api.ApiService
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MusicListFragment: Fragment() {

    lateinit var rvUserList: RecyclerView
    lateinit var songAdapter: SongAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {

        const val MUSIC_KEY = "MUSIC_TYPE"

        fun newInstance(musicType: Int) : MusicListFragment {
            val fragment = MusicListFragment()
            val bundle = Bundle()
            bundle.putInt(MUSIC_KEY, musicType)
            fragment.arguments = bundle
            return fragment
        }

    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycle_view, container, false)
        rvUserList = view.findViewById((R.id.rv_user_list))
        layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        val musicType = arguments?.getInt(MUSIC_KEY)
        switchMusicType(musicType)
        swipeRefreshLayout.setOnRefreshListener{
            swipeRefreshLayout.isRefreshing = true
            switchMusicType(musicType)
        }
        return view
    }

    private fun switchMusicType(musicType: Int?) {
        var callMusicRes: Call<SongResponse>? = when (musicType) {
            1-> ApiService.createRetrofit().create(ApiService::class.java).getRockSongs()
            2-> ApiService.createRetrofit().create(ApiService::class.java).getClassicSongs()
            3-> ApiService.createRetrofit().create(ApiService::class.java).getPopSongs()
            else-> throw Exception()
        }
        if (callMusicRes != null) {
            startRetrofit(callMusicRes)
        }
    }


    private fun startRetrofit(something: Call<SongResponse>) {
        something.enqueue(object : Callback<SongResponse> {
            override fun onResponse(
                call: Call<SongResponse>,
                response: Response<SongResponse>
            ) {
                if (response.isSuccessful) {
                    swipeRefreshLayout.isRefreshing = false
                    songAdapter = SongAdapter(response.body()!!.results)
                    rvUserList.adapter = songAdapter;
                    rvUserList.layoutManager = layoutManager;
                }
            }

            override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}

