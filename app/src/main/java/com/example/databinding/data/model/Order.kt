package com.example.databinding.data.model

data class Order(
    val restaurant: Restaurant,
    val items: List<MenuItem>,
    val deliveryCost: Int
) {
    val subtotal: Int
        get() = items.fold(0) { acc, menuItem -> acc + (menuItem.cost * menuItem.quantity) }
}
