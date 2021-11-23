package com.example.kotlinNetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import retrofit2.Call
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinNetwork.Adapter.AdapterCoin
import com.example.kotlinNetwork.Common.CoinObj
import com.example.kotlinNetwork.Interface.RetrofitInterface
import com.example.kotlinNetwork.Model.Coin
import com.example.kotlinNetwork.Model.CoinList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response


class CoinActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var mService: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerCoinList: RecyclerView;
    var Lise: List<Coin> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        recyclerCoinList = findViewById(R.id.recyclerCoinList)
        layoutManager = LinearLayoutManager(this)
        recyclerCoinList.layoutManager = layoutManager

        launch(Dispatchers.Main) {
            mService = CoinObj.retrofitService
            mService.getCoinList().enqueue(object : Callback<CoinList> {
                override fun onFailure(call: Call<CoinList>, t: Throwable) {

                }

                override fun onResponse(call: Call<CoinList>, response: Response<CoinList>) {
                    Lise = response.body()?.returndata()!!
                    recyclerCoinList.adapter = AdapterCoin(Lise)

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


