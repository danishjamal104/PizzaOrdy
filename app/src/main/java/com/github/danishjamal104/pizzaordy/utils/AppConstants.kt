package com.github.danishjamal104.pizzaordy.utils

object AppConstants {
    const val BASE_URL = "https://android.free.beeceptor.com/"
    const val DEFAULT_PIZZA_JSON = """
{
   "name":"Non-Veg Pizza",
   "isVeg":false,
   "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
   "defaultCrust":1,
   "crusts":[
      {
         "id":1,
         "name":"Hand-tossed",
         "defaultSize":2,
         "sizes":[
            {
               "id":1,
               "name":"Regular",
               "price":235.0
            },
            {
               "id":2,
               "name":"Medium",
               "price":265.0
            },
            {
               "id":3,
               "name":"Large",
               "price":295.0
            }
         ]
      },
      {
         "id":2,
         "name":"Cheese Burst",
         "defaultSize":1,
         "sizes":[
            {
               "id":1,
               "name":"Medium",
               "price":295.0
            },
            {
               "id":2,
               "name":"Large",
               "price":325.0
            }
         ]
      }
   ]
}
"""
}