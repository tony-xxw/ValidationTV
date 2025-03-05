package com.aispeech.validationtv.bean

import android.util.Log
import com.google.gson.annotations.SerializedName

data class VocabData(
    var action: String?=null,
    var categories: String?=null,
    @SerializedName("class_name")
    var className: String?=null,
    var data: String?=null,
    var extraList: List<Extra?>?=null,
    var flags: Int?=null,
    @SerializedName("package_name")
    var packageName: String?=null,
    var type: String?=null
) {

}

data class Extra(
    var key: String?,
    var type: String?,
    var value: String?
)

data class VocabWrapper(
    var vocab: String,
    var intent: VocabData
)