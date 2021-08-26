package rs.sloman.albums.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    val userId : Int,
    val title : String,
){
    @PrimaryKey
    var id: Int = 0
}
