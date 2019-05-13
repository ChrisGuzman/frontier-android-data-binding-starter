package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding.data.OrderRepository
import com.example.databinding.data.model.Order

class MainViewModel : ViewModel() {
    private val repository = OrderRepository()
    val order: LiveData<Order> = repository.getOpenOrder()
    val tip = MutableLiveData<Int>().apply { value = 0 }
    val total: LiveData<Int> = MediatorLiveData<Int>().apply {
        value = 0
        addSource(order) { newOrder -> value = calculateTotal(newOrder, tip.value) }
        addSource(tip) { newTip -> value = calculateTotal(order.value, newTip) }
    }

    private fun calculateTotal(order: Order?, tip: Int?): Int {
        if (order == null) return 0
        val tipCost = if (tip != null) (tip / 100) * order.subtotal else 0
        return order.subtotal + tipCost + order.deliveryCost
    }
}
