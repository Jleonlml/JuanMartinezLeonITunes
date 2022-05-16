package com.example.juanmartinezleonitunes.Adapter

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.juanmartinezleonitunes.Model.Song
import com.example.juanmartinezleonitunes.R
import com.squareup.picasso.Picasso


//create the viewholder
//add the data to the viewholder
class SongAdapter(private val list: List<Song>, private val musicType: Int?): RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(randomSong: Song) {
            val tvSongName = itemView.findViewById<TextView>(R.id.tv_song_name)
            val ivSongPic = itemView.findViewById<ImageView>(R.id.iv_songImg)
            val tvArtistName = itemView.findViewById<TextView>(R.id.tv_artist_name)
            val tvPrice = itemView.findViewById<TextView>(R.id.tv_price)
            val cardItem = itemView.findViewById<CardView>(R.id.card_item)

            tvSongName.text = randomSong.trackName
            Picasso.get().load(randomSong.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(ivSongPic)

            tvArtistName.text = randomSong.artistName
            tvPrice.text = if(randomSong.trackPrice > 0) "$" + randomSong.trackPrice.toString() else "FREE"

            when(musicType) {
                1->
                    cardItem.setCardBackgroundColor(Color.parseColor("#90EEEEEE"))
                2->{
                    cardItem.setCardBackgroundColor(Color.parseColor("#301a1300"))
                    tvPrice.setTextColor(Color.parseColor("#F2F2F2"))
                    tvSongName.setTextColor(Color.parseColor("#F2F2F2"))
                    tvArtistName.setTextColor(Color.parseColor("#F2F2F2"))
                }
                3->
                    cardItem.setCardBackgroundColor(Color.parseColor("#90EEEEEE"))
            }

            itemView.setOnClickListener()
            {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(Uri.parse(randomSong.previewUrl), "audio/*")


                itemView.context.startActivity(intent) //from  w ww.  j ava 2s  .  c om
            }

        }
    }

    // we inflate our list item and pass it to our viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val listItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_list, parent, false)

        return SongViewHolder(listItem)
    }

    // this is where we bind the data to the view
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    // return the size of the list
    override fun getItemCount(): Int {
        return list.size
    }
}