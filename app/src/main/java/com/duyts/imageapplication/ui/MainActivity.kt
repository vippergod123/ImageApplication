package com.duyts.imageapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.duyts.imageapplication.R
import com.duyts.imageapplication.adapter.DataResponse
import com.duyts.imageapplication.adapter.ImageAdapter
import com.duyts.imageapplication.adapter.ImageViewPagerAdapter
import com.duyts.imageapplication.databinding.ActivityMainBinding
import com.duyts.imageapplication.repository.AppRepository
import com.duyts.imageapplication.util.Resource
import com.duyts.imageapplication.util.Utils
import com.duyts.imageapplication.viewmodel.MainViewModel
import com.duyts.imageapplication.viewmodel.ViewModelProviderFactory

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewBinding: ActivityMainBinding

    override fun initFirst() {
        super.initFirst()

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }

    override fun initView() {
        super.initView()
        viewBinding.bottomRecyclerView.run {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        viewBinding.imageSliderViewPager.setPageTransformer(MarginPageTransformer(50))
    }

    override fun initLogic() {
        super.initLogic()

        viewModel.profile.observe(this, {
            it.getContentIfNotHandled()?.let { response ->
                val data: DataResponse? = response.data
                when (response) {
                    is Resource.Success -> {
                        Log.d("CHRIS", data.toString())
                        viewBinding.imageSliderViewPager.adapter =
                            ImageViewPagerAdapter(this, data?.profile?.images ?: emptyList())
                        viewBinding.bottomRecyclerView.run {
                            adapter = ImageAdapter(
                                applicationContext,
                                data?.profile?.images ?: emptyList()
                            )
                        }
                        viewBinding.avatarImageView.apply {
                            Glide.with(this@MainActivity).load(R.drawable.user_icon).circleCrop().into(this)
                        }
                        viewBinding.usernameTextView.text = data?.profile?.name

                        viewBinding.displayNameTextView.text = data?.profile?.name
                        viewBinding.dobTextView.text = Utils.milisecondToDobFormat(data?.profile?.birthdate!! * 1000)
                    }

                    is Resource.Failed -> {
                        Log.e("CHRIS", response.data.toString())
                    }

                    is Resource.Loading -> {
                        Log.w("CHRIS", response.data.toString())
                    }
                }
            }
        })

        viewModel.getProfile()
    }
}