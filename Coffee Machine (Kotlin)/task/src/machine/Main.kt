package machine

data class CoffeeMachine(
    val water: Int = 400,
    val milk: Int = 540,
    val beans: Int = 120,
    val cups: Int = 9,
    val money: Int = 550
)

data class Coffee(val water: Int, val milk: Int, val beans: Int, val price: Int)

val coffees = mapOf(
    1 to Coffee(250, 0, 16, 4), // espresso
    2 to Coffee(350, 75, 20, 7), // latte
    3 to Coffee(200, 100, 12, 6), // cappuccino
)

fun printState(machine: CoffeeMachine) {
    println()
    println("The coffee machine has:")
    println("${machine.water} ml of water")
    println("${machine.milk} ml of milk")
    println("${machine.beans} g of coffee beans")
    println("${machine.cups} disposable cups")
    println("\$${machine.money} of money")
    println()
}

fun canMakeCoffee(machine: CoffeeMachine, coffee: Coffee): Boolean =
    machine.water >= coffee.water &&
    machine.milk >= coffee.milk &&
    machine.beans >= coffee.beans &&
    machine.cups > 0

fun makeCoffee(machine: CoffeeMachine, coffee: Coffee): CoffeeMachine =
    machine.copy(
        water = machine.water - coffee.water,
        milk = machine.milk - coffee.milk,
        beans = machine.beans - coffee.beans,
        cups = machine.cups - 1,
        money = machine.money + coffee.price
    )

fun buyCoffee(machine: CoffeeMachine): CoffeeMachine {
    println()
    println("What do you want to buy? " +
            "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    val choice = readln().trim()
    if (choice == "back") return machine

    val coffee = coffees[choice.toInt()]!!
    val missing = missingResource(machine,coffee)
    if (missing != null) {
        println("Sorry, not enough $missing!")
        println()
        return machine
    }

    println("I have enough resources, making you a coffee!")
    println()
    return makeCoffee(machine,coffee)
}

fun missingResource(machine: CoffeeMachine, coffee: Coffee): String? =
    when {
        machine.water < coffee.water -> "water"
        machine.milk < coffee.milk -> "milk"
        machine.beans < coffee.beans -> "coffee beans"
        machine.cups < 1 -> "disposable cups"
        else -> null
    }

fun fillSupplies(machine: CoffeeMachine): CoffeeMachine {
    println()
    println("Write how many ml of water you want to add: ")
    val addWater = readln().trim().toInt()
    println("Write how many ml of milk you want to add: ")
    val addMilk = readln().trim().toInt()
    println("Write how many grams of coffee beans you want to add: ")
    val addBeans = readln().trim().toInt()
    println("Write how many disposable cups you want to add: ")
    val addCups = readln().trim().toInt()
    println()

    return machine.copy(
        water = machine.water + addWater,
        milk = machine.milk + addMilk,
        beans = machine.beans + addBeans,
        cups = machine.cups + addCups
    )
}

fun takeMoney(machine: CoffeeMachine): CoffeeMachine {
    println()
    println("I gave you \$${machine.money}")
    println()
    return machine.copy(money =  0)
}

fun main() {
    var machine = CoffeeMachine()

    while (true) {
        println("Write action (buy, fill, take, remaining, exit): ")
        when (readln().trim()) {
            "buy" -> machine = buyCoffee(machine)
            "fill" -> machine = fillSupplies(machine)
            "take" -> machine = takeMoney(machine)
            "remaining" -> printState(machine)
            "exit" -> return
        }
    }
}