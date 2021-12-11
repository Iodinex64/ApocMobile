package com.iodine.apocmobile.models

class Location constructor(
    var name: String,
    var bio: String,
    var worldName: String = ""
) {
    var landmarks = ArrayList<Landmark>()
    var creatures = ArrayList<Creature>()
    var races = ArrayList<Race>()
    private var population: Int = 0

    fun calculatePop() {
        //sum all landmark populations
        population = 0
        for (landmark in landmarks) {
            population += landmark.population
        }
    }

    fun addLandmark(l: Landmark) {
        landmarks.add(l)
    }

    fun addCreature(cr: Creature) {
        creatures.add(cr)
    }

    fun getPop(): Int { return population }

    override fun toString(): String {
        return "Location: $name (About: $bio | World Name: $worldName | Population: $population)"
    }
}