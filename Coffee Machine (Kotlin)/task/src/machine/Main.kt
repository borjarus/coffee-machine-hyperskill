package machine

data class Supplies(val water: Int, val milk: Int, val beans: Int)

fun readAmount(prompt: String): Int {
    print("$prompt ")
    return readln().toInt()
}

fun main() {
    val supplies = Supplies(
        readAmount("Write how many ml of water the coffee machine has:"),
        readAmount("Write how many ml of milk the coffee machine has:"),
        readAmount("Write how many grams of coffee beans the coffee machine has:")
    )

    val cups = readAmount("Write how many cups of coffee you will need:")

    val maxCups = listOf(
        supplies.water / 200,
        supplies.milk / 50,
        supplies.beans / 15
    ).min()

    when {
        cups < maxCups -> "Yes, I can make that amount of coffee (and even ${maxCups - cups} more than that)"
        cups == maxCups -> "Yes, I can make that amount of coffee"
        else -> "No, I can make only $maxCups cups of coffee"
    }.also(::println)
}
