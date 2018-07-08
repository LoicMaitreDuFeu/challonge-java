/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Participant] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
class ParticipantAdapter internal constructor() : JsonDeserializer<Participant> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Participant {
        val e = jsonElement as JsonObject

        val id = e.get("id").asLong
        val updatedAt = context.deserialize<OffsetDateTime>(e.get("updated_at"), OffsetDateTime::class.java)
        val groupId = check(e.get("group_id"))?.asLong
        val createdAt = context.deserialize<OffsetDateTime>(e.get("created_at"), OffsetDateTime::class.java)
        val tournamentId = e.get("tournament_id").asLong
        val seed = e.get("seed").asInt
        val name = check(e.get("name"))?.asString
        val displayNameWithInvitationEmailAddress = check(e.get("display_name_with_invitation_email_address"))?.asString
        val misc = check(e.get("misc"))?.asString
        val challongeUsername = check(e.get("challonge_username"))?.asString
        val inviteEmail = check(e.get("invite_email"))?.asString
        val active = e.get("active").asBoolean
        val attachedParticipatablePortraitUrl = check(e.get("attached_participatable_portrait_url"))?.asString
        val canCheckIn = e.get("can_check_in").asBoolean
        val challongeEmailAddressVerified = check(e.get("challonge_email_address_verified"))?.asString
        val checkedIn = e.get("checked_in").asBoolean
        val checkedInAt = context.deserialize<OffsetDateTime?>(e.get("checked_in_at"), OffsetDateTime::class.java)
        val confirmRemove = e.get("confirm_remove").asBoolean
        val emailHash = check(e.get("email_hash"))?.asString
        val finalRank = check(e.get("final_rank"))?.asInt
        val icon = check(e.get("icon"))?.asString
        val invitationId = check(e.get("invitation_id"))?.asLong
        val invitationPending = e.get("invitation_pending").asBoolean
        val onWaitingList = e.get("on_waiting_list").asBoolean
        val participatableOrInvitationAttached = e.get("participatable_or_invitation_attached").asBoolean
        val reactivatable = e.get("reactivatable").asBoolean
        val removable = e.get("removable").asBoolean
        val username = check(e.get("username"))?.asString
        val matches = context.deserialize<List<Match>>(e.get("matches"), object : TypeToken<List<Match>>() {}.type)

        return Participant(id = id, updatedAt = updatedAt, groupId = groupId, createdAt = createdAt,
                tournamentId = tournamentId, seed = seed, name = name,
                displayNameWithInvitationEmailAddress = displayNameWithInvitationEmailAddress, misc = misc,
                challongeUsername = challongeUsername, inviteEmail = inviteEmail, active = active,
                attachedParticipatablePortraitUrl = attachedParticipatablePortraitUrl, canCheckIn = canCheckIn,
                challongeEmailAddressVerified = challongeEmailAddressVerified, checkedIn = checkedIn,
                checkedInAt = checkedInAt, confirmRemove = confirmRemove, emailHash = emailHash, finalRank = finalRank,
                icon = icon, invitationId = invitationId, invitationPending = invitationPending, onWaitingList = onWaitingList,
                participatableOrInvitationAttached = participatableOrInvitationAttached, reactivatable = reactivatable,
                removable = removable, username = username, matches = matches)
    }

    private fun check(element: JsonElement): JsonElement? {
        return if (element.isJsonNull) null
        else element
    }
}
