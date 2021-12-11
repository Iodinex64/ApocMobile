package com.iodine.apocmobile.models

class World constructor(var name: String) {
    var characters = arrayListOf<Character>()
    var locations = arrayListOf<Location>()

    override fun toString(): String {
        return "World: $name (Characters: " + characters.size + " | Locations: " + locations.size + ")"
    }
}