import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ronda") val ronda: Int?,
    @ColumnInfo(name = "fecha") val fecha: String?
)