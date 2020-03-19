import java.awt.Color
import java.awt.Graphics

class Player (var x: Double, var y: Double) {
    var right: Boolean = false
    var left: Boolean = false
    val width: Int = 40
    val height: Int = 10

    fun tick() {
        if (right) {
            this.x++
        } else if (left) {
            this.x--
        }

        if (x + width > Game.globals.WIDTH) x = Game.globals.WIDTH - width.toDouble()
        else if (x < 0) x = 0.0
    }

    fun render(g: Graphics) {
        g.color = Color.BLUE
        g.fillRect(x.toInt(), y.toInt(), width, height)
    }
}
