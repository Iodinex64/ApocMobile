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
import android.net.Uri

object DataManager {
    var masterWorlds = ArrayList<World>()
    var masterCharacters = ArrayList<Character>()

    var masterLocations = ArrayList<Location>()

    var masterLandmarks = ArrayList<Landmark>()

    var selectedItemIndex = 0

    fun createWorld(name: String) {
        val w = World(name)
        masterWorlds.add(w)
        println("World Count: " + masterWorlds.size)
    }

    
    fun editWorld(n: String, index: Int) {
        masterWorlds[index].name = n
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

    fun createCharacter(name: String, race: String, world: World, bio: String, URI: Uri) {
        val c = Character(name, race, bio, world.name, URI)
        masterCharacters.add(c)
        if (masterWorlds.contains(world)) {
            world.characters.add(c)
        }
        println("Characters Count: " + masterCharacters.size)
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
                masterLandmarks.addAll(loc.landmarks)
            }
            //we're done
            reader.close()

            println("Data successfully loaded.")
            println("   Worlds loaded: " + masterWorlds.size)
            println("   Locations loaded: " + masterLocations.size)
            println("   Characters loaded: " + masterCharacters.size)
            println("   Landmarks loaded: " + masterLandmarks.size)
        } catch (e: Exception) {
            println("Couldn't load from database, making new one...")
            saveToJSON(c)
            readFromJSON(c)
        }
    }
}