package com.example.pokemonapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.PokemonItemBinding
import com.example.pokemonapp.model.Pokemon


class PokemonAdapter(private val dataSet: Pokemon) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private lateinit var binding: PokemonItemBinding

    class ViewHolder(view: PokemonItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = PokemonItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val pokemonResponse: com.example.pokemonapp.model.Result = dataSet.results.get(position)
        binding.pokemonInformation = pokemonResponse

   /*     viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("userId",""+dataSet.get(position).userId)
            bundle.putSerializable("userUrl",""+dataSet.get(position).url)

            val navigationController = Navigation.findNavController(viewHolder.itemView)
            // navigationController.navigate(R.id.action_userFragment_to_postFragment,bundle!!)

        }*/
    }
    override fun getItemCount() = dataSet.results.size

}