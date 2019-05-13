package com.example.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.data.MenuItemAdapter
import com.example.databinding.data.model.Order
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val adapter = MenuItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuItemsRecyclerView.adapter = adapter
        menuItemsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        submit.setOnClickListener(this::onClickSubmitButton)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
            order.observe(this@MainActivity, Observer {
                updateOrderDetails(it)
                adapter.menuItems = it.items
            })
            tip.observe(this@MainActivity, Observer {
                // TODO: updateTip(it)
            })
            total.observe(this@MainActivity, Observer {
                updateTotal(it)
            })
        }
    }

    private fun updateOrderDetails(order: Order) {
        restaurantName.text = order.restaurant.name
        restaurantAddress.text = order.restaurant.address
        checkoutSubtotal.text =
            getString(R.string.currency_usd_format, (order.subtotal / 100f))
        deliveryCost.text =
            getString(R.string.currency_usd_format, (order.deliveryCost / 100f))
        adapter.menuItems = order.items
    }

    private fun updateTip(tip: Int?) {
        // TODO: update the tip amount
    }

    private fun updateTotal(total: Int?) {
        checkoutTotal.text = getString(R.string.currency_usd_format, (total ?: 0) / 100f)
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
