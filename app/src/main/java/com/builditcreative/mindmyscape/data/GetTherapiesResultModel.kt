package com.builditcreative.mindmyscape.data

import com.google.gson.annotations.SerializedName

data class GetTherapiesResultModel(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("data")
    var data: ArrayList<Data> = arrayListOf(),
    val isLoading: Boolean = false
)

data class Data(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image_nm") var imageNm: String? = null,
    @SerializedName("image_path") var imagePath: String? = null,
    @SerializedName("profile") var profile: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)