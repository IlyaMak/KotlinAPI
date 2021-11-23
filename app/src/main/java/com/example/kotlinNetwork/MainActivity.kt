package com.example.kotlinNetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinNetwork.Adapter.AdapterCharacter
import com.example.kotlinNetwork.Common.Marvel
import com.example.kotlinNetwork.Interface.RetrofitMarvelInterface
import com.example.kotlinNetwork.Model.MarvelResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class MainActivity : AppCompatActivity() , CoroutineScope by MainScope() {

    lateinit var mService: RetrofitMarvelInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: AdapterCharacter
    lateinit var recyclerCharacterList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Marvel.retrofitMarvelService
        recyclerCharacterList = findViewById(R.id.recyclerCharacterList)
        layoutManager = LinearLayoutManager(this)
        recyclerCharacterList.layoutManager = layoutManager

        launch(Dispatchers.Main) {
            mService = Marvel.retrofitMarvelService
            val timestamp = (System.currentTimeMillis() / 1000).toInt().toString()
            Log.d("timestamp", timestamp)
            val apiKey = ""
            Log.d("apiKey", apiKey)
            val input = timestamp + "" + apiKey
            Log.d("input", input)
            val hash = BigInteger(1, MessageDigest.getInstance("MD5").digest(
                input.toByteArray())).toString(16).padStart(32, '0')
            mService.getCharacterList(timestamp, apiKey, hash).enqueue(
                object : Callback<MarvelResponse> {
                    override fun onFailure(call: Call<MarvelResponse>, t: Throwable) {
                        Log.d("error2", t.message!!)
                    }

                    override fun onResponse(call: Call<MarvelResponse>,
                                            response: Response<MarvelResponse>
                    ) {
                        Log.d("success", response.code().toString())
                        val marvelResponse = response.body() as MarvelResponse
                        adapter = AdapterCharacter(baseContext, marvelResponse.data.results)
                        adapter.notifyDataSetChanged()
                        recyclerCharacterList.adapter = adapter
                    }
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recipe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                val intent = Intent(this, MainActivity::class.java).apply {}
                startActivity(intent)
                return true
            }
            R.id.item2 -> {
                val intent = Intent(this, CoinActivity::class.java).apply {}
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}