class Resource private() {
	println("Starting transcation")
	
	private def cleanup()  {println("Cleanning up")}

	def op1() = println("op1")
	def op2() = println("op2")
	def op3() = println("op3")
	def op4() = println("op4")
}

object Resource {
	def usage(func:Resource => Unit) {
		val resource = new Resource

		try{
			func(resource)
		} finally {
			resource.cleanup
		}
	}
}

def fyi(resource:Resource) {
	resource.op1
	resource.op2
	resource.op4
	resource.op3
}

Resource.usage{resource => resource.op1;resource.op3}
