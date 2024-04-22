package com.example.harrypotter.ui.characterList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListViewModel : CharacterListViewModel by viewModels()
    private lateinit var characterListAdapter: CharacterListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterListBinding.inflate(layoutInflater,container,false).apply {
            viewModel = characterListViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupObserver()
        onClick()
    }

    private fun onClick() {
        binding.refreshBtn.setOnClickListener {
            characterListViewModel.getCharacters()
        }

        characterListAdapter.itemClickCallBack = { id ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(id))
        }
    }

    private fun setupObserver() {
        characterListViewModel.apply {
            getCharacters()

            characterList.observe(viewLifecycleOwner){dataList ->
                if(dataList.isNotEmpty()){
                    if(this@CharacterListFragment :: characterListAdapter.isInitialized){
                        characterListAdapter.addCharacterItem(dataList)
                    }
                }
            }
        }
    }

    private fun initViews() {
        characterListAdapter = CharacterListAdapter()

        with(binding.characterRV){
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = characterListAdapter
        }
    }

}