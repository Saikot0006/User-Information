package com.example.harrypotter.ui.characterList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
                binding.item = data
            }
        }


    fun addCharacterItem(dataItemList : List<CharacterListResponse.CharacterListResponseItem>){
        dataList.clear()
        dataList.addAll(dataItemList)
        notifyDataSetChanged()
    }
}