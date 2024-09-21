package dev.jaysonguillen.guidomia

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.jaysonguillen.guidomia.databinding.ActivityMainBinding
import dev.jaysonguillen.guidomia.databinding.DialogFilterMakeBinding
import dev.jaysonguillen.guidomia.presentation.adapter.CarsAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.MakeAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.ModelAdapter
import dev.jaysonguillen.guidomia.presentation.viewmodel.CarsViewModel
import dev.jaysonguillen.guidomia.presentation.viewmodel.CarsViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CarsViewModelFactory
    private lateinit var viewModel: CarsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var carsAdapter: CarsAdapter
    private lateinit var makeAdapter: MakeAdapter
    private lateinit var modelAdapter: ModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[CarsViewModel::class.java]

        initCars()
        showFilterMakeDialog()

    }



   private fun showFilterMakeDialog() {
        binding.tvFilterMake.setOnClickListener {
            val dialog = Dialog(this)
            val binding = DialogFilterMakeBinding.inflate(layoutInflater)
            dialog.setContentView(binding.root)

            makeAdapter = MakeAdapter()
            binding.rvFilterMake.apply {
                adapter = makeAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            viewModel.getCarsJson().observe(this){
                makeAdapter.differ.submitList(it)
            }
            // Show the dialog
            dialog.show()
        }
    }


    private fun initCars(){
        carsAdapter = CarsAdapter()
        binding.rvCars.apply {
            adapter = carsAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.getCarsJson().observe(this){
            carsAdapter.differ.submitList(it)
        }

    }
}