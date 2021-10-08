package com.metehanbolat.graduateproject.view.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.databinding.FragmentFoodDetailsBinding
import com.metehanbolat.graduateproject.mostusedfunctions.DivideWords
import com.metehanbolat.graduateproject.viewmodel.FoodDetailsFragmentViewModel

class FoodDetailsFragment : Fragment() {

    private lateinit var binding : FragmentFoodDetailsBinding
    private lateinit var viewModel : FoodDetailsFragmentViewModel
    private lateinit var auth: FirebaseAuth

    private lateinit var foodName : String
    private lateinit var foodUrl : String
    private lateinit var foodImage : String
    private var foodId = 0
    private var foodPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FoodDetailsFragmentViewModel by viewModels()
        viewModel = tempViewModel
        auth = Firebase.auth

        arguments?.let {
            foodId = FoodDetailsFragmentArgs.fromBundle(it).foodId
            foodName = FoodDetailsFragmentArgs.fromBundle(it).foodName
            foodPrice = FoodDetailsFragmentArgs.fromBundle(it).foodPrice
            foodUrl = FoodDetailsFragmentArgs.fromBundle(it).foodUrl
            foodImage = FoodDetailsFragmentArgs.fromBundle(it).foodImage
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_details, container, false)
        binding.fragmentFoodDetails = this
        binding.argFoodName = foodName
        binding.argFoodPrice = foodPrice

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.moveFoodsFragment(binding.root)
            } }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        viewModel.imageAnimateFirst(requireContext(), binding.foodImage)

        val foodInfo = viewModel.getFoodDetails(requireContext(), foodName)
        binding.foodInformation = foodInfo

        viewModel.loadFoodImage(foodUrl, binding.foodImage)

        return binding.root
    }

    fun moveFoodsFragment(view : View){
        viewModel.moveFoodsFragment(view)
    }

    fun decreaseButton(){
        if (binding.orderAmount.text.toString().toInt() != 1){
            val pairList = viewModel.decreaseOrIncrease(requireContext(), binding.orderAmount.text.toString().toInt(), foodPrice, -1)
            binding.orderAmount.text = pairList.first
            binding.foodPrice.text = pairList.second
        }
    }

    fun increaseButton(){
        if (binding.orderAmount.text.toString().toInt() >= 1){
            val pairList = viewModel.decreaseOrIncrease(requireContext(), binding.orderAmount.text.toString().toInt(), foodPrice, 1)
            binding.orderAmount.text = pairList.first
            binding.foodPrice.text = pairList.second
        }
    }

    fun addToCart(v : View){
        val uniqueUser = DivideWords()
        viewModel.addFoodInBasket(foodId, foodName, foodImage, (foodPrice * binding.orderAmount.text.toString().toInt()), binding.orderAmount.text.toString(), uniqueUser.getFirstPart(auth.currentUser!!.email.toString()))
        viewModel.cartAnimate(requireContext(), v)
        viewModel.imageAnimateZoomOut(binding.foodImage)
        viewModel.lottieAnimation(binding.lottie)

        object  : CountDownTimer(1500, 1000){
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                viewModel.cartAnimateBack(requireContext(), v)
                viewModel.imageAnimate(requireContext(), binding.foodImage)
            }
        }.start()
    }

}