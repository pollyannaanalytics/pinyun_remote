
// 定義 lambda
    val kgsToPounds = {x: Double -> x * 2.20344}
    val poundsToUSTons = {x: Double -> x / 2000.0}


// 寫一個接受 lambda 的 conbine function
fun combine(lambda1: (Double) -> Double, lambda2: (Double) -> Double): (Double) -> Double{
    return {x: Double -> lambda1(lambda2(x))}
}

// 結合兩個lambda
val kgsToUsTons = combine(kgsToPounds, poundsToUSTons)

typealias DoubleConvension = (Double) -> Double



fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}