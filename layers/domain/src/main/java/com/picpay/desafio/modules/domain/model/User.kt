package com.picpay.desafio.modules.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val img: String,
    val name: String,
    val username: String
) : Parcelable