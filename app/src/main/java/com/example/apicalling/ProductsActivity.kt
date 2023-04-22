package com.example.apicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apicalling.databinding.ActivityProductsBinding
import com.google.gson.Gson

class ProductsActivity : AppCompatActivity() {
    lateinit var mRequestQueue: RequestQueue
    lateinit var binding: ActivityProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {
        mRequestQueue = Volley.newRequestQueue(this)
        var id=intent.getIntExtra("id",0)
        var req = JsonObjectRequest(
            Request.Method.GET, "https://dummyjson.com/products/$id", null,
            { response ->

                val productlist = Gson().fromJson(response.toString(), ProductsItem::class.java)

                binding.txtTitleproduct.text=productlist.title
                binding.txtid.text= productlist.id.toString()
                binding.txtbrand.text= productlist.brand
                binding.txtprice.text= productlist.price.toString()
                binding.txtdiscountPercentage.text= productlist.discountPercentage.toString()
                binding.txtdescription.text= productlist.description
                binding.txtrating.text= productlist.rating.toString()
                binding.txtstock.text= productlist.stock.toString()
                binding.txtcategory.text= productlist.category

                val adapter=ImageAdapter(this,productlist.images)
                binding.viewpager.adapter=adapter

            },
            { error ->
                Log.e("TAG", "initview: " + error.message)
            }
        )
        mRequestQueue.add(req)
    }
}