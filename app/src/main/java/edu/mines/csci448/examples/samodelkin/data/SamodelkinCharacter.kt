package edu.mines.csci448.examples.samodelkin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "character")
data class SamodelkinCharacter(val name: String,
                               val race: String,
                               val profession: String,
                               val dexterity: Int,
                               val wisdom: Int,
                               val strength: Int,
                               val intelligence: Int,
                               val constitution: Int,
                               val charisma: Int,
                               @ColumnInfo(name = "avatar")
                               @SerializedName("avatar")
                               val avatarAssetPath: String? = null,
                               @PrimaryKey
                               val id: UUID = UUID.randomUUID())