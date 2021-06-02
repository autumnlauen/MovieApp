package com.example.movieinfoapp.actor.actorNetwork

import java.time.LocalDate

data class ActorProfile(
    val id: Int,
    val profile_path: String?,
    val name: String,
    val birthday: String?,
    val deathday: String?,
    val place_of_birth: String?
) {

    val placeOfBirth: String
        get() {
            return if (place_of_birth != null) {
                "Born in $place_of_birth"
            } else {
                "Birthplace not available"
            }
        }

    val age: String
        get() {

            if (birthday != null && birthday.length >= 4) {

                val birth = LocalDate.parse(birthday)

                return if (deathday != null) {
                    val death = LocalDate.parse(deathday)

                    "Died: " + birth.until(death).years.toString() + " years"

                } else {
                    val today = LocalDate.now()

                    "Age: " + birth.until(today).years.toString() + " years"
                }
            } else {
                return "Age not available"
            } //what happens if birthday available and not deathday or vice versa?
        }
}
