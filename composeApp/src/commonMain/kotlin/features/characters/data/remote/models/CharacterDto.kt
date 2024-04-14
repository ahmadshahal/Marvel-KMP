package features.characters.data.remote.models

import core.data.models.ThumbnailResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnail")
    val thumbnail: ThumbnailResponse?
)
