package com.example.apicalling

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apicalling.databinding.ActivityMainBinding
import com.example.apicalling.databinding.DialogItemFileBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        val dialog = Dialog(this)
        val dialogBinding: DialogItemFileBinding = DialogItemFileBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

//        if (dialog.isShowing.equals(dialogBinding.progressbar)) {
//            dialog.show()
//        } else {
//            dialog.dismiss()
//        }

        mRequestQueue = Volley.newRequestQueue(this)

        var req = JsonObjectRequest(
            Request.Method.GET, "https://dummyjson.com/products", null,

            { response ->
                Log.e("TAG", "initview: " + response)
                val productlist =
                    Gson().fromJson(response.toString(), ProductsModelClass::class.java)


                var adapter = ProductsAdapter(this, productlist) {
                    var i = Intent(this, ProductsActivity::class.java)
                    i.putExtra("id", it.id)
                    startActivity(i)
                }
                val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rcvproducts.layoutManager = manager
                binding.rcvproducts.adapter = adapter

            },
            { error ->
                Log.e("TAG", "initview: " + error.message)
            }
        )
        mRequestQueue.add(req)
    }
}
