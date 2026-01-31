package com.example.zwigato

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
sealed interface OrderStatus : Parcelable {
    data object Confirmed: OrderStatus
    data object Preparing: OrderStatus
    data object EnRoute : OrderStatus
    data object Arriving : OrderStatus
    data object Delivered : OrderStatus
}