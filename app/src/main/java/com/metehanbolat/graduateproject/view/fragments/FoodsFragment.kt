package com.metehanbolat.graduateproject.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.recycler.adapter.RecyclerFoodAdapter
import com.metehanbolat.graduateproject.databinding.FragmentFoodsBinding
import com.metehanbolat.graduateproject.viewmodel.FoodsFragmentViewModel

class FoodsFragment : Fragment() {

    private lateinit var binding : FragmentFoodsBinding
    private lateinit var viewModel : FoodsFragmentViewModel
    private lateinit var adapter : RecyclerFoodAdapter
    private var auth : FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel :FoodsFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods, container, false)
        binding.foodsFragment = this

        viewModel.backPressed(requireActivity())

        viewModel.foodList.observe(viewLifecycleOwner, { foodList ->
            adapter = RecyclerFoodAdapter(requireContext(), foodList, viewModel, auth)
            binding.foodAdapter = adapter
        })

        return binding.root
    }

    fun button(v : View){
        viewModel.moveBasket(v)
    }

    fun signOut(){
        viewModel.exitCurrentUser(requireContext(), requireActivity())
    }

}