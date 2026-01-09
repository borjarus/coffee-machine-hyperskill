package machine

data class Ingredients(val water: Int, val milk: Int, val beans: Int)

fun main() {
    val cups = readln()
        .toIntOrNull()
        ?.takeIf { it > 0 }
        ?: run {
            println("Invalid input")
            return
        }

    val ingredients = listOf(200, 50, 15)
        .map { it * cups }
        .let { Ingredients(it[0], it[1], it[2]) }

    """
For ${'$'}cups cups of coffee you will need:
${ingredients.water} ml of water
${ingredients.milk} ml of milk
${ingredients.beans} g of coffee beans
    """.trimIndent().also (::println )
}
