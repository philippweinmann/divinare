package com.example.divinare.restaurants

import com.example.divinare.parser.RestaurantEntryHandler

class RestaurantGeneration/// constructor
    (internal var getRestaurantEntry: RestaurantEntryHandler) {

    private val oxford = RestaurantEntry(
        "Oxford Pub - Bier & Burger",
        "https://www.google.co.jp/maps/place/Oxford+Pub+-+Bier+%26+Burger/@49.0087485,8.4131141,19.5z/data=!4m8!1m2!2m1!1soxford+karlsruhe!3m4!1s0x0:0x823aadf9ebe8bb1d!8m2!3d49.0086067!4d8.4129224",
        "€"
    )
    private val yangda = RestaurantEntry(
        "Yangda",
        "https://www.google.co.jp/maps/place/Yangda+Restaurant/@49.0108268,8.4076694,16z/data=!4m8!1m2!2m1!1sKarlsruhe+restaurants!3m4!1s0x0:0x6da146d1822f440c!8m2!3d49.0106492!4d8.39619",
        "€€"
    )
    private val bangkokFoodland = RestaurantEntry(
        "Bangkok Foodland",
        "https://www.google.de/maps/place/Bangkok+Foodland/@49.0095302,8.4065934,17z/data=!3m1!4b1!4m5!3m4!1s0x4797073e0a05f015:0x8a00b012300e4753!8m2!3d49.0095267!4d8.4087874",
        "€"
    )
    private val continent = RestaurantEntry(
        "Restaurant Continent",
        "https://www.google.de/maps/place/Restaurant+Continent/@49.0092548,8.4085357,18.25z/data=!4m12!1m6!3m5!1s0x4797073e0a05f015:0x8a00b012300e4753!2sBangkok+Foodland!8m2!3d49.0095267!4d8.4087874!3m4!1s0x410b91e33b3f6e77:0x3fa257953012edc2!8m2!3d49.0092641!4d8.4073137",
        "€"
    )


    /// create an array with all restaurants inside
    fun addBasicListOfRestaurants() {
        getRestaurantEntry.addRestaurantEntry(oxford)
        getRestaurantEntry.addRestaurantEntry(yangda)
        getRestaurantEntry.addRestaurantEntry(bangkokFoodland)
        getRestaurantEntry.addRestaurantEntry(continent)
    }
}