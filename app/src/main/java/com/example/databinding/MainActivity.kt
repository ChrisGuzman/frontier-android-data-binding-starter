package com.example.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.data.MenuItemAdapter
import com.example.databinding.data.model.Order
import com.example.databinding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val adapter = MenuItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
        }

        menuItemsRecyclerView.adapter = adapter
        menuItemsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        submit.setOnClickListener(this::onClickSubmitButton)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
            binding.viewModel = this
            order.observe(this@MainActivity, Observer {
                adapter.menuItems = it.items
            })
            tip.observe(this@MainActivity, Observer {

            })
            total.observe(this@MainActivity, Observer {
            })
        }
    }

    fun onClickSubmitButton(view: View) {
        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_submit_title)
            .setMessage(R.string.dialog_submit_body)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }
}
