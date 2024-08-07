package com.picpay.desafio.modules.data.network.schema

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?
) : Parcelable