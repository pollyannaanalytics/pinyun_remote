
//Q4. Create a new class, Human, includes:

open class Human (val name: String = ""){
    open var mana = false // Q6: confirm whether human has mena or not, no mana is default

    open fun attack(){
        println("$name use First Attack!")
    }
}



//Q5. Create a subclass of Human; name it Mage, includes:

class Mage: Human{
    constructor(name: String = ""): super(name)

    override  var mana = true

    override fun attack(){
       println("$name use Fireball!")
    };

}


fun main(args: Array<String>) {
    //Q4 answer
    val instanceOfHuman = Human()
    instanceOfHuman.attack()

    //Q5 answer
    val instanceOfMage = Mage()
    instanceOfMage.attack()


    //Q6 answer
    println("Do human has Mana? Answer: ${instanceOfHuman.mana}")
    println("Do Mage has Mana? Answer: ${instanceOfMage.mana}")

}