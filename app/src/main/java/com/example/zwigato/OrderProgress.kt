package com.example.zwigato

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderProgress(
    val orderStatus: OrderStatus,
    val progress: Int,
): Parcelable
