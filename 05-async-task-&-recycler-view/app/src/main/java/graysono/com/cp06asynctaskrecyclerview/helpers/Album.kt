package graysono.com.cp06asynctaskrecyclerview.helpers

data class Album(
    var name: String,
    var playCount: Int,
    var artist: String,
    var image: String
) {
    override fun toString(): String {
        return "Album(name='$name', playCount=$playCount, artist='$artist', image='$image')"
    }
}