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
    CoffeeMachine(
        machine.water - coffee.water,
        machine.milk - coffee.milk,
        machine.beans - coffee.beans,
        machine.cups - 1,
        machine.money + coffee.price
    )


fun buyCoffee(machine: CoffeeMachine): CoffeeMachine {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
    val choice = readln().trim().toInt()

    val coffee = coffees[choice]
    return if (coffee != null && canMakeCoffee(machine, coffee)) {
        makeCoffee(machine, coffee)
    } else {
        machine
    }
}

fun fillSupplies(machine: CoffeeMachine): CoffeeMachine {
    print("Write how many ml of water you want to add: ")
    val water = readln().trim().toInt()
    print("Write how many ml of milk you want to add: ")
    val milk = readln().trim().toInt()
    print("Write how many grams of coffee beans you want to add: ")
    val beans = readln().trim().toInt()
    print("Write how many disposable cups you want to add: ")
    val cups = readln().trim().toInt()

    return CoffeeMachine(
        machine.water + water,
        machine.milk + milk,
        machine.beans + beans,
        machine.cups + cups,
        machine.money
    )
}

fun takeMoney(machine: CoffeeMachine): CoffeeMachine {
    println("I gave you \$${machine.money}")
    return CoffeeMachine(machine.water, machine.milk, machine.beans, machine.cups, 0)
}

fun main() {
    var machine = CoffeeMachine()

    println("The coffee machine has:")
    printState(machine)

    println("Write action (buy, fill, take): ")
    when (readln().trim()) {
        "buy" -> machine = buyCoffee(machine)
        "fill" -> machine = fillSupplies(machine)
        "take" -> machine = takeMoney(machine)
    }

    println("\nThe coffee machine has:")
    printState(machine)
}