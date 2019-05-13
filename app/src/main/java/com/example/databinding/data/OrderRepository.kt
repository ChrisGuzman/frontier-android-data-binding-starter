package com.example.databinding.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databinding.data.model.MenuItem
import com.example.databinding.data.model.Order
import com.example.databinding.data.model.Restaurant

class OrderRepository {
    fun getOpenOrder(): LiveData<Order> =
        MutableLiveData<Order>().apply {
            value = Order(
                restaurant = Restaurant(
                    name = "Nerd's Tex-mex",
                    address = "5 Restaurant Ave\nAtlanta, GA"
                ),
                items = listOf(
                    MenuItem(name = "Burrito Bowl", quantity = 1, cost = 599),
                    MenuItem(name = "Tinga Tacos", quantity = 3, cost = 199),
                    MenuItem(name = "Nachos", quantity = 1, cost = 399)
                ),
                deliveryCost = 350
            )
        }
}
