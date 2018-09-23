package pokemonmapautowalking.cs3231.com.pokemonmapautowalking.pokemon

import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.R

class Pokedex(){
    private var pokedex = ArrayList<Pokemon>();
    val size:Int
        get() = this.pokedex.count()
    init {
        this.pokedex = ArrayList<Pokemon>();
    }

    fun load(){
        this.pokedex.add(
                Pokemon("Charmander",
                        "The flame that burns at the tip of its tail is an indication of its emotions. The flame wavers when Charmander is enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.",
                        R.drawable.charmander,
                        100,
                        13.613273, 100.835483,
                        false))

        this.pokedex.add(
                Pokemon("Bulbasaur",
                        "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
                        R.drawable.bulbasaur,
                        150,
                        13.613325, 100.834791,
                        false))

        this.pokedex.add(
                Pokemon("Squirtle",
                        "Whenever Pikachu comes across something new, it blasts it with a jolt of electricity. If you come across a blackened berry, it's evidence that this Pokémon mistook the intensity of its charge.",
                        R.drawable.squirtle,
                        200,
                        13.611135, 100.839197,
                        false))

        //TODO change place
        this.pokedex.add(
                Pokemon("Pikachu",
                        "Squirtle's shell is not merely used for protection. The shell's rounded shape and the grooves on its surface help minimize resistance in water, enabling this Pokémon to swim at high speeds.",
                        R.drawable.pikachu,
                        200,
                        //13.612664, 100.839490
                        13.612664, 100.839490,
                        false))

        //TODO change place
        this.pokedex.add(
                Pokemon("Genger",
                        "Sometimes, on a dark night, your shadow thrown by a streetlight will suddenly and startlingly overtake you. It is actually a Gengar running past you, pretending to be your shadow.",
                        R.drawable.gengar,
                        200,
                        //13.613497, 100.837403
                        13.613497, 100.837403,
                        false))
    }

    fun getPokemon(index:Int):Pokemon?{
        var result = Pokemon();
        if(0<=index&&index<this.pokedex.count()) {
            result = this.pokedex.get(index);
        }
        return result;
    }
}