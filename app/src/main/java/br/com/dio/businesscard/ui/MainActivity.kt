package br.com.dio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.databinding.ActivityMainBinding
import br.com.dio.businesscard.di.App
import br.com.dio.businesscard.util.Image
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private val mainViewModel: MainViewModel by inject()

        private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerCards.adapter = adapter
        getAllBusinessCard()
        insertLister()

    }

    private fun insertLister() {
        binding.floatingButton.setOnClickListener {
            val intent = Intent(this, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card ->
            Image.share(this@MainActivity, card)

        }

    }
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this,{businessCard ->
            adapter.submitList(businessCard)

        })
    }


}