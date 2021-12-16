package com.iodine.apocmobile.models

import android.net.Uri


class Character constructor(
    var name: String = "",
    var race: String = "",
    var bio: String = "",
    var worldName: String = "",
    var URI: Uri
) {
    override fun toString(): String {
        return "Character: $name ($race | Bio: $bio | World Name: $worldName | URI: $URI)"
    }
}