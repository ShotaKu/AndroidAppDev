package com.shota.pokemonmap

data class Pokemon(val name:String,
                   val des:String,
                   val image:Int,
                   val power: Int,
                   val lat: Double,
                   val long:Double,
                   var isCatched: Boolean){

}