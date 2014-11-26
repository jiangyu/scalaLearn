import scala.actors.Actor._

def sumOfFactory(number:Int) = {
	(0 /: (1 to number)){(sum,i) => if(number%i == 0) sum+i; else sum}
}

def isPerfect(candidate:Int) = candidate*2 == sumOfFactory(candidate)

def isPerfect2(candidate:Int) = {
	val range = 100000
	val partition = candidate/range 
	val caller = self
	for(i <- 0 until partition) {
		actor {
			if(i != (partition-1)) {
				caller ! (i*range to ((i+1)*range-1)).filter(isPerfect(_)).mkString(",")
			} else {
				caller ! (i*range to candidate).filter(isPerfect(_)).mkString(",")
			}
		}		
	}
	
	(0 until partition).foreach{ item:Int => 
		receive {
                        case msg:String  => if(msg!="") println(msg)
                }
	}
}

val number = 10000000
//println((0 to number).filter(isPerfect(_)).mkString(","))
println(isPerfect2(number))
