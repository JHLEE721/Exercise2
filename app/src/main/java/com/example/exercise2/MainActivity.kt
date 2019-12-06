package com.example.exercise2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.exercise2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.databinding.DataBindingUtil as DataBindingUtil1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil1.setContentView(this, R.layout.activity_main)

        binding.buttonReset.setOnClickListener{
            resetButton()
        }
        binding.buttonCalculate.setOnClickListener{
            calculateBMI()
        }
    }

    private fun resetButton(){
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
        binding.editTextWeight.text = null
        binding.editTextHeight.text = null
        binding.imageViewProfile.setImageResource(R.drawable.empty)
        binding.textViewBMI.text = "BMI : "
    }

    private fun calculateBMI(){
        Toast.makeText(this, "Calculated", Toast.LENGTH_SHORT).show()

        val bmi: Double = binding.editTextWeight.text.toString().toDouble() / ((binding.editTextHeight.text.toString().toDouble()/100)*(binding.editTextHeight.text.toString().toDouble()/100))
        val format: Double = String.format("%.1f", bmi).toDouble()

        binding.apply {

            when {
                bmi < 18.5 -> {
                    binding.imageViewProfile.setImageResource(R.drawable.under)
                    binding.textViewBMI.text = "Underweight : BMI " + format.toString()
                }
                bmi in 18.5..24.9 -> {
                    binding.imageViewProfile.setImageResource(R.drawable.normal)
                    binding.textViewBMI.text = "Normal : BMI " + format.toString()
                }
                bmi >= 25 -> {
                    binding.imageViewProfile.setImageResource(R.drawable.over)
                    binding.textViewBMI.text = "Overweight : BMI " + format.toString()
                }
            }
        }
    }
}
