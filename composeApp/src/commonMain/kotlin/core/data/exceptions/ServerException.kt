package core.data.exceptions

data class ServerException(
    override val message: String,
    val code: String
) : Exception(message)