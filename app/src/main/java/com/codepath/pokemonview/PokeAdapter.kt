package com.codepath.pokemonview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class PokeAdapter(private val pokeList: MutableList<Triple<String, String, String>>) : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage: ImageView
        val pokeName: TextView
        val pokeWeight: TextView

        init {
            pokeImage = view.findViewById(R.id.poke_image)
            pokeName = view.findViewById(R.id.poke_name)
            pokeWeight = view.findViewById(R.id.poke_weight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(pokeList[position].first)
            .centerCrop()
            .into(holder.pokeImage)



        val pokeNameText = holder.pokeName
        val pokemonNameFetch = pokeList[position].second
        pokeNameText.text = pokeList[position].second

        holder.pokeImage.setOnClickListener{
            Toast.makeText(holder.itemView.context, "You tapped on $pokemonNameFetch! ", Toast.LENGTH_SHORT).show()
        }

        val pokeWeightText = holder.pokeWeight
        pokeWeightText.text = pokeList[position].third

    }

    override fun getItemCount() = pokeList.size
}


