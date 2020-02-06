package com.example.divinare.parser


import com.example.divinare.restaurants.RestaurantEntry

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.Scanner
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.regex.*

class RestaurantEntryHandler {

    private val fileName = "restaurantlist.txt"
    val restaurantEntries = ArrayList<RestaurantEntry>()

    val amountOfRestaurantEntries: Int
        get() = restaurantEntries.size


    @Throws(IOException::class)
    fun convert(inputStream: InputStream, charset: Charset): String {

        val stringBuilder = StringBuilder()
        var line: String? = null

        val bufferedReader = BufferedReader(InputStreamReader(inputStream, charset))
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line)
        }

        return stringBuilder.toString()
    }

    /// method reads in the restaurantentries from the text file in the raw folder. The method should be called upon onCreate(...).
    @Throws(IOException::class)
    fun readInRestaurantEntry(restaurantEntryStream: InputStream) {
        val fileContents = convert(restaurantEntryStream, StandardCharsets.UTF_8)

        // Initialise String patterns
        val namePattern = Pattern.compile("Name:[\\s\\t]*[^;]*")
        val locationLinkPattern = Pattern.compile("GoogleMapsLink:[\\s\\t]*[^;]*")
        val pricingPattern = Pattern.compile("Pricing:[\\s\\t]*[^;]*")

        var nameLine: String
        var locationLinkLine: String
        var pricingLine: String
        var name: Array<String>
        var locationLink: Array<String>
        var pricing: Array<String>

        val nameMatcher = namePattern.matcher(fileContents)
        val locationLinkMatcher = locationLinkPattern.matcher(fileContents)
        val pricingMatcher = pricingPattern.matcher(fileContents)

        while (nameMatcher.find() && locationLinkMatcher.find() && pricingMatcher.find()) {
            // get the restaurant name
            nameLine = nameMatcher.group()
            name = nameLine.split(": ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            // get the locationLink
            locationLinkLine = locationLinkMatcher.group()
            locationLink =
                locationLinkLine.split(": ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            // get the pricing
            pricingLine = pricingMatcher.group()
            pricing =
                pricingLine.split(": ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            restaurantEntries.add(RestaurantEntry(name[1], locationLink[1], pricing[1]))
        } // restaurantEntries are now read in.
    }

    @Throws(IOException::class)
    private fun readFile(file: String): String {
        val reader = BufferedReader(FileReader(file))
        var line: String? = null
        val stringBuilder = StringBuilder()
        val ls = System.getProperty("line.separator")

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line)
                stringBuilder.append(ls)
            }

            return stringBuilder.toString()
        } finally {
            reader.close()
        }
    }

    fun getRestaurantEntry(ArraylistIndex: Int): RestaurantEntry {
        return restaurantEntries[ArraylistIndex]
    }

    /// adds a restaurantEntry to the array, not the restaurantlist.txt file directly.
    fun addRestaurantEntry(newRestaurantEntry: RestaurantEntry) {
        restaurantEntries.add(newRestaurantEntry)
    }

    /// TODO Fix this method to be usable in android!
    // Following methods have the goal to allow for writing the restaurantEntries onto the specific file.
    @Throws(IOException::class)
    fun createRestaurantEntry(restaurantEntry: RestaurantEntry) {
        // let's write the entry onto the file
        val out = BufferedWriter(FileWriter(fileName, true))
        out.write("{")
        out.newLine()
        out.write("\t" + "Name: " + restaurantEntry.getName() + ";")
        out.newLine()
        out.write("\t" + "GoogleMapsLink: " + restaurantEntry.getLocationLink() + ";")
        out.newLine()
        out.write("\t" + "Pricing: " + restaurantEntry.getPricing() + ";")
        out.newLine()
        out.write("}")
        out.newLine()

        out.close()
    }
}
