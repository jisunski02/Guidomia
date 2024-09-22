package dev.jaysonguillen.guidomia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import dev.jaysonguillen.guidomia.databinding.ActivityMainBinding
import dev.jaysonguillen.guidomia.databinding.DialogFilterMakeBinding
import dev.jaysonguillen.guidomia.databinding.DialogFilterModelBinding
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
    @Inject
    lateinit var carsAdapter: CarsAdapter
    @Inject
    lateinit var makeAdapter: MakeAdapter
    @Inject
    lateinit var modelAdapter: ModelAdapter
    private val viewModel: CarsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCars()
        showFilterByMakeDialog()
        showFilterByModelDialog()

    }

    private fun initCars(){
        binding.rvCars.apply {
            adapter = carsAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.getCarsJson().observe(this){ carsAssets->
            if(carsAssets.isEmpty()){
                viewModel.getCars().observe(this){ carsLocal->
                    carsAdapter.differ.submitList(carsLocal)
                }
            }

            else{
                viewModel.getCars().observe(this){ carsLocal->
                    if(carsLocal.isEmpty()){
                        Log.e("savedToLocal", "saved")
                        carsAdapter.differ.submitList(carsAssets)
                        viewModel.saveCars(carsAssets)
                    }
                    else{
                        carsAdapter.differ.submitList(carsLocal)
                    }
                }
            }

        }

    }

   private fun showFilterByMakeDialog() {

        binding.tvFilterMake.setOnClickListener {
            val dialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialog)
            val binding = DialogFilterMakeBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(binding.root)

            binding.rvFilterMake.apply {
                adapter = makeAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            viewModel.getCarsJson().observe(this){
                val carMakes = mutableListOf("All")
                carMakes.addAll(it.map { car->
                    car.make
                })
                makeAdapter.differ.submitList(carMakes)

            }

            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            bottomSheet?.setBackgroundResource(R.drawable.bg_bottom_dialog)
            dialog.show()

            makeAdapter.setOnItemClickLister {
                dialog.dismiss()
                filterCarsByMake(it)
            }

        }
    }

    private fun showFilterByModelDialog() {

        binding.tvFilterModel.setOnClickListener {
            val dialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialog)
            val binding = DialogFilterModelBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(binding.root)

            binding.rvFilterModel.apply {
                adapter = modelAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            viewModel.getCarsJson().observe(this){
                val carModel = mutableListOf("All")
                carModel.addAll(it.map { car->
                    car.model
                })
                modelAdapter.differ.submitList(carModel)

            }

            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            bottomSheet?.setBackgroundResource(R.drawable.bg_bottom_dialog)
            dialog.show()

            modelAdapter.setOnItemClickLister {
                dialog.dismiss()
                filterCarsByModel(it)
            }

        }
    }


    private fun filterCarsByMake(makeFilter: String){
        viewModel.getCarsJson().observe(this){ cars->
            val filteredList = cars.filter {
                it.make.contains(makeFilter, ignoreCase = true)
            }

            if(makeFilter == "All") {
                carsAdapter.differ.submitList(cars)
                carsAdapter.notifyItemChanged(0)
                binding.tvFilterMake.text = "Any make"
            } else{
                carsAdapter.differ.submitList(filteredList)
                binding.tvFilterMake.text = makeFilter
            }
        }
    }

    private fun filterCarsByModel(modelFilter: String){
        viewModel.getCarsJson().observe(this){ cars->
            val filteredList = cars.filter {
                it.model.contains(modelFilter, ignoreCase = true)
            }

            if(modelFilter == "All") {
                carsAdapter.differ.submitList(cars)
                carsAdapter.notifyItemChanged(0)
                binding.tvFilterModel.text = "Any make"
            } else{
                carsAdapter.differ.submitList(filteredList)
                binding.tvFilterModel.text = modelFilter
            }
        }
    }
}