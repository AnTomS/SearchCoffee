package com.atom.searchcoffe.domain.dto

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable



data class CartItem(val coffee: Coffee, var quantity: Int)