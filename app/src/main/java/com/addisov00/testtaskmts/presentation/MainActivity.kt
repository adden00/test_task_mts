package com.addisov00.testtaskmts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.addisov00.testtaskmts.R
import com.addisov00.testtaskmts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}