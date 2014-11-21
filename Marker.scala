class Marker(val color:String) {
	override def toString = "hello " + color;
}

object Marker{
	def primary = "red, green, blue"
}

println(Marker.primary)
