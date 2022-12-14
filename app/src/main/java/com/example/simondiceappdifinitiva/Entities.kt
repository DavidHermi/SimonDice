import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Record")
data class Entities (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "record") val record: Int?,
)