package com.example.zwigato

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class OrderViewModel: ViewModel() {

    val orderState: StateFlow<OrderProgress> = getOrderStatusFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = OrderProgress(OrderStatus.Confirmed,0)
    )

    fun getOrderStatusFlow(): Flow<OrderProgress> = flow {
        val totalDuration = 20000L // 20 seconds total
        val tickInterval = 100L    // Update every 100ms for 60fps-like smoothness
        val totalTicks = totalDuration / tickInterval

        for (i in 0..totalTicks) {
            val progress = ((i.toFloat() / totalTicks) * 100).toInt()

            val currentStatus = when {
                progress < 25 -> OrderStatus.Confirmed
                progress < 50 -> OrderStatus.Preparing
                progress < 75 -> OrderStatus.EnRoute
                progress < 95 -> OrderStatus.Arriving
                else -> OrderStatus.Delivered
            }

            emit(OrderProgress(currentStatus, progress))
            delay(tickInterval)
        }
    }
}