import java.awt.Color
import java.awt.Graphics

class Enemy(var x: Double, var y: Double) {
    val width: Int = 40
    val height: Int = 10

    fun tick() {
        x+= (Game.globals.ball.x - x - 6) * 0.05
    }

    fun render(g: Graphics) {
        g.color = Color.red
        g.fillRect(x.toInt(), y.toInt(), width, height)
    }
}