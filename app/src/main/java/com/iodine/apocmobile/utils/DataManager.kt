package com.iodine.apocmobile.utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.FileWriter
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths
//we need all of them anyway lol
import com.iodine.apocmobile.models.*
import android.content.Context

object DataManager {
    var masterWorlds = ArrayList<World>()
    var masterCharacters = ArrayList<Character>()
    var masterRaces = ArrayList<Race>()
    var masterLocations = ArrayList<Location>()
    var masterCreatures = ArrayList<Creature>()
    var masterLandmarks = ArrayList<Landmark>()

    var selectedItemIndex = 0

    fun createWorld(name: String) {
        val w = World(name)
        masterWorlds.add(w)
        println("World Count: " + masterWorlds.size)
    }

    
    fun editWorld(w: World, index: Int) {
        println("Replaced " + masterWorlds[index].toString())
        masterWorlds[index] = w
        println("With $w")
    }

    fun editCharacter(newC: Character, index: Int) {
        println("Replaced " + masterCharacters[index].toString())
        for (world in masterWorlds) {
            if (world.characters.contains(masterCharacters[index])) {
                val i = world.characters.indexOf(masterCharacters[index])
                world.characters[i] = newC
                masterCharacters[index] = newC
                println("With $newC")
            }
        }
    }

    fun editLocation(newL: Location, index: Int) {
        println("Replaced " + masterLocations[index].toString())
        for (world in masterWorlds) {
            if (world.locations.contains(masterLocations[index])) {
                val i = world.locations.indexOf(masterLocations[index])
                world.locations[i] = newL
                masterLocations[index] = newL
                println("With $newL")
            }
        }
    }

    fun editCreature(newCR: Creature, index: Int) {
        println("Replaced " + masterCreatures[index].toString())
        for (world in masterWorlds) {
            for (location in world.locations) {
                if (location.creatures.contains(masterCreatures[index])) {
                    val i = location.creatures.indexOf(masterCreatures[index])
                    location.creatures[i] = newCR
                    masterCreatures[index] = newCR
                    println("With $newCR")
                }
            }
        }
    }

    fun editRace(newR: Race, index: Int) {
        println("Replaced " + masterRaces[index].toString())
        for (world in masterWorlds) {
            for (location in world.locations) {
                if (location.races.contains(masterRaces[index])) {
                    val i = location.races.indexOf(masterRaces[index])
                    location.races[i] = newR
                    masterRaces[index] = newR
                    println("With $newR")
                }
            }
        }
    }

    fun editLandmark(newLA: Landmark, index: Int) {
        println("Replaced " + masterLandmarks[index].toString())
        for (world in masterWorlds) {
            for (location in world.locations) {
                if (location.landmarks.contains(masterLandmarks[index])) {
                    val i = location.landmarks.indexOf(masterLandmarks[index])
                    location.landmarks[i] = newLA
                    masterLandmarks[index] = newLA
                    location.calculatePop()
                    println("With $newLA")
                }
            }
        }
    }

    fun removeWorldAtIndex(index: Int) {
        println("Removing world: " + masterWorlds[index])
        masterWorlds.removeAt(index)
        println("Done removing.")
    }

    fun removeCharacterAtIndex(index: Int) {
        val c = masterCharacters[index]
        println("Removing location: $c")
        for (world in masterWorlds) {
            if (world.characters.contains((c))) {
                world.characters.remove(c)
            }
        }
        masterCharacters.remove(c)
        println("Done removing.")
    }

    fun removeLocationAtIndex(index: Int) {
        val l = masterLocations[index]
        println("Removing location: $l")
        for (world in masterWorlds) {
            if (world.locations.contains((l))) {
                world.locations.remove(l)
            }
        }
        masterLocations.remove(l)
        println("Done removing.")
    }

    fun removeCreatureAtIndex(index: Int) {
        val cr = masterCreatures[index]
        println("Removing creature: $cr")
        for (world in masterWorlds) {
            for (location in world.locations)
                if (location.creatures.contains((cr))) {
                    location.creatures.remove(cr)
                    println("Done removing.")
                }
        }
        masterCreatures.remove(cr)
    }

    fun removeRaceAtIndex(index: Int) {
        val r = masterRaces[index]
        println("Removing creature: $r")
        for (world in masterWorlds) {
            for (location in world.locations)
                if (location.races.contains((r))) {
                    location.races.remove(r)
                    println("Done removing.")
                }
        }
        masterRaces.remove(r)
    }

    fun removeLandmarkAtIndex(index: Int) {
        val la = masterLandmarks[index]
        println("Removing landmark: $la")
        for (world in masterWorlds) {
            for (location in world.locations)
                if (location.landmarks.contains((la))) {
                    location.landmarks.remove(la)
                    println("Done removing.")
                }
        }
        masterLandmarks.remove(la)
    }

    fun createLocation(name: String, bio: String, w: World) {
        val l = Location(name, bio, w.name)
        masterLocations.add(l)
        if (masterWorlds.contains(w)) {
            w.locations.add(l)
            println("Locations Count: " + masterLocations.size)
        }
    }

    fun createCharacter(name: String, race: Race, world: World, bio: String) {
        val c = Character(name, race, bio, world.name)
        masterCharacters.add(c)
        if (masterWorlds.contains(world)) {
            world.characters.add(c)
        }
        println("Characters Count: " + masterCharacters.size)
    }

    fun createCreature(name: String, home: Location, bio: String) {
        val cr = Creature(name, bio, home.name)
        masterCreatures.add(cr)
        if (masterLocations.contains(home)) {
            home.addCreature(cr)
        }
        println("Creatures Count: " + masterCreatures.size)
    }

    fun createRace(name: String, home: Location, bio: String) {
        val r = Race(name, bio, home.name)
        masterRaces.add(r)
        if (masterLocations.contains(home)) {
            home.races.add(r)
        }
        println("Races Count: " + masterRaces.size)
    }

    fun createLandmark(name: String, bio: String, population: Int, loc: Location) {
        val la = Landmark(name, bio, loc.name, population)
        masterLandmarks.add(la)
        if (masterLocations.contains(loc)) {
            loc.addLandmark(la)
            loc.calculatePop()
            println("Landmarks Count: " + masterLandmarks.size)
        }
    }

    //DANGEROUS LOL
    fun NukeAll(c: Context) {
        masterWorlds.clear()
        saveToJSON(c)
        readFromJSON(c)
    }

    fun saveToJSON(c: Context) {
        println("Writing to database...")
        val dir = c.filesDir
        val writer = FileWriter("" + dir + "ApocryphaDatabase.json")
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        gson.toJson(masterWorlds, writer)
        writer.flush()
        writer.close()
        println("Data successfully stored.")
    }

    fun readFromJSON(c: Context) {
        try {
            println("Loading from database...")
            val dir = c.filesDir
            val reader: Reader = Files.newBufferedReader(Paths.get("" + dir + "ApocryphaDatabase.json"))
            val jsonWorlds: ArrayList<World> = Gson().fromJson(reader, object : TypeToken<ArrayList<World?>?>() {}.type)
            masterWorlds.addAll(jsonWorlds)

            //repopulate master lists
            for (wor in jsonWorlds) {
                masterLocations.addAll(wor.locations)
                masterCharacters.addAll(wor.characters)
            }
            for (loc in masterLocations) {
                masterRaces.addAll(loc.races)
                masterLandmarks.addAll(loc.landmarks)
                masterCreatures.addAll(loc.creatures)
            }
            //we're done
            reader.close()

            println("Data successfully loaded.")
            println("   Worlds loaded: " + masterWorlds.size)
            println("   Locations loaded: " + masterLocations.size)
            println("   Characters loaded: " + masterCharacters.size)
            println("   Races loaded: " + masterRaces.size)
            println("   Creatures loaded: " + masterCreatures.size)
            println("   Landmarks loaded: " + masterLandmarks.size)
        } catch (e: Exception) {
            println("Couldn't load from database, making new one...")
            saveToJSON(c)
            readFromJSON(c)
        }
    }
}