package edu.mines.csci448.examples.samodelkin.util

import android.content.Context
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter

object CharacterGenerator {
    private fun <T> List<T>.rand() = shuffled().first()
    private fun Int.roll(sides: Int = 6) = (0 until this)
        .sumOf { (1..sides).toList().rand() }

    private val firstNames = listOf("Eli", "Alex", "Sophie")
    private val lastNames = listOf("Lightweaver", "Greatfoot", "Oakenfield")
    private val races = listOf("Dwarf", "Elf", "Human", "Troll", "Goblin")
    private val professions = listOf("Knight", "Wizard", "Archer", "Mage", "Monk")

    private fun name() = "${firstNames.rand()} ${lastNames.rand()}"
    private fun race() = races.rand()
    private fun profession() = professions.rand()
    private fun dex() = 4.roll(7)
    private fun wis() = 3.roll(8)
    private fun str() = 5.roll(6)
    private fun inl() = 4.roll(7)
    private fun cha() = 5.roll(5)
    private fun con() = 3.roll(7)
    private fun avatar(context: Context?): String? {
        val assetFile = context?.assets?.list("characters")?.random()
        return if(assetFile != null) {
            "file:///android_asset/characters/$assetFile"
        } else {
            null
        }
    }

    /**
     * creates a SamodelkinCharacter with random stats
     * @param context current context used for asset loading of avatar images
     */
    fun generateRandomCharacter(context: Context? = null) = SamodelkinCharacter(name = name(),
                                                                                race = race(),
                                                                                profession = profession(),
                                                                                dexterity = dex(),
                                                                                wisdom = wis(),
                                                                                strength = str(),
                                                                                intelligence = inl(),
                                                                                charisma = cha(),
                                                                                constitution = con(),
                                                                                avatarAssetPath = avatar(context))
}