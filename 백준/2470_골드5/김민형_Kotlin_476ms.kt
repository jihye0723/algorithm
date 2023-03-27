import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    /*
5
-2 4 -99 -1 98
     */
    val n = readLine()!!.toInt()
    val data = readLine()!!.split(' ').map { it.toInt() }.sorted()
    val result = IntArray(2) {0}

    var left = 0
    var right = n-1
    var min = Int.MAX_VALUE

    while (left != right) {
        val sub = data[left] + data[right]
        if (abs(sub) < min) {
            min = abs(sub)
            result[0] = data[left]
            result[1] = data[right]
        }
        if (sub > 0) right--
        else left++
    }

    print("${result[0]} ${result[1]}")
}
