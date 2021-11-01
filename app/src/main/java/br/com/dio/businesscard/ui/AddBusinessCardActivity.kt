package br.com.dio.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.dio.businesscard.di.App
import org.koin.android.ext.android.inject

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddBusinessCardBinding.inflate(layoutInflater)
    }

//    private val mainViewModel: MainViewModel by inject()

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertLister()


    }

    private fun insertLister() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.buttonConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                company = binding.tilName.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                backgroundColor = binding.tilColor.editText?.text.toString()

            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}