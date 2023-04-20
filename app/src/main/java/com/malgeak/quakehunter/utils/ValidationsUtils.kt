package com.malgeak.quakehunter.utils

class ValidationsUtils {

    companion object {
        fun validUserName(name: String): Boolean {
            var validation = true

            if (name.isNullOrBlank()
                || name.replace(" ", "").contentEquals("")
            ) {
                validation = false
            }

            return validation
        }

        fun validPassword(password: String, confirmPassword: String): Boolean {
            var validation = true

            if (password.isNullOrBlank()
                || password.replace(" ", "").contentEquals("")
                || !password.contentEquals(confirmPassword)
            ) {
                validation = false
            }

            return validation
        }
    }
}
