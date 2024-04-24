package com.example.harrypotter.ui.characterList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ItemCharactersBinding
import com.example.harrypotter.models.CharacterListResponse

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>(){

    private val dataList : MutableList<CharacterListResponse.CharacterListResponseItem> = mutableListOf()
    var itemClickCallBack: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(ItemCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val data = dataList[position]
        if(holder is CharacterListViewHolder){
            holder.bind(data)

            holder.itemView.setOnClickListener {
                itemClickCallBack?.invoke(data.id ?: "")
            }
        }
    }

    class CharacterListViewHolder(private val binding: ItemCharactersBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(data : CharacterListResponse.CharacterListResponseItem){

                Glide.with(binding.characterItemIV.context).load(data.image).placeholder(R.drawable.profile_pic).into(binding.characterItemIV)

                if (data.name.isNullOrEmpty()) {
                    binding.characterItemNameTV.visibility = View.GONE
                } else {
                    binding.characterItemNameTV.visibility = View.VISIBLE
                    binding.characterItemNameTV.text = data.name
                }

                if (data.actor.isNullOrEmpty()) {
                    binding.characterItemActorNameTV.visibility = View.GONE
                } else {
                    binding.characterItemActorNameTV.visibility = View.VISIBLE
                    binding.characterItemActorNameTV.text = data.actor
                }

                if (data.house.isNullOrEmpty()) {
                    binding.houseNameTv.visibility = View.GONE
                } else {
                    binding.houseNameTv.visibility = View.VISIBLE
                    binding.houseNameTv.text = data.house
                }
            }
        }


    fun addCharacterItem(dataItemList : List<CharacterListResponse.CharacterListResponseItem>){
        dataList.clear()
        dataList.addAll(dataItemList)
        notifyDataSetChanged()
    }
}