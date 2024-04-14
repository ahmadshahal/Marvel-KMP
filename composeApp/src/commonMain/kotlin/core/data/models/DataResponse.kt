package core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    @SerialName("status")
    val status: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: T,
)
