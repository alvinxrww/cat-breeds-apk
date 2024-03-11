package com.example.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvCats: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvCats = binding.rvCats
        rvCats.setHasFixedSize(true)

        list.addAll(getListCat())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvCats.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvCats.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent =Intent(this@MainActivity, AboutMe::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListCat(): ArrayList<Cat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataLink = resources.getStringArray(R.array.details_link)
        val listCats = ArrayList<Cat>()
        for (i in dataName.indices) {
            val cat = Cat(dataName[i], dataDescription[i], dataPhoto[i], dataLink[i])
            listCats.add(cat)
        }
        return listCats
    }

    private fun showRecyclerList() {
        rvCats.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListCatAdapter(list)
        rvCats.adapter = listCatAdapter

        listCatAdapter.setOnItemClickCallback(object : ListCatAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cat) {
                showSelectedCat(data)
            }
        })
    }

    private fun showSelectedCat(cat: Cat) {
        Toast.makeText(this, "Kamu memilih " + cat.name, Toast.LENGTH_SHORT).show()
    }
}