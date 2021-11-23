package com.example.kotlinNetwork.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.kotlinNetwork.Model.MarvelCharacter
import com.example.kotlinNetwork.R

class AdapterCharacter(private val context: Context, private val marvelCharacterList: MutableList<MarvelCharacter>):
    RecyclerView.Adapter<AdapterCharacter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageCharacter: ImageView = itemView.findViewById(R.id.image_character)
        var textName: TextView = itemView.findViewById(R.id.text_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent,
            false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = marvelCharacterList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val path = marvelCharacterList[position].thumbnail.path
        val extension = marvelCharacterList[position].thumbnail.extension
        val fullPath = path.substring(0, 4) + 's' + path.substring(4) + "." + extension

        Picasso.get().load(fullPath).into(holder.imageCharacter)
        holder.textName.text = marvelCharacterList[position].name
    }

}