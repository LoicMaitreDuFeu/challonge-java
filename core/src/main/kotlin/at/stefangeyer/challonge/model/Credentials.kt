package at.stefangeyer.challonge.model

import java.util.*

/**
 * Challonge credentials containing username and api-key.
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
data class Credentials(private val username: String, private val key: String) {
    /**
     * Creates a HTTP basic auth String from the given credentials
     *
     * @return HTTP basic auth String
     */
    fun toHttpAuthString(): String {
        val credentials = this.username + ":" + this.key
        return "Basic " + Base64.getEncoder().encodeToString(credentials.toByteArray())
    }
}
