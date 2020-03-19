import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.util.Random

class Ball (var x: Double, var y: Double) {
    val width: Int = 5
    val height: Int = 5

    var dx: Double = 0.0
    var dy: Double = 0.0
    val speed: Double = 1.25

    init {
        val angle: Int = Random().nextInt(120 - 45) + 45 + 1
        dx = Math.cos(Math.toRadians(angle.toDouble()))
        dy = Math.sin(Math.toRadians(angle.toDouble()))
    }

    fun tick() {
        x += dx * speed
        y += dy * speed

        if ( x + (dx * speed) + width >= Game.globals.WIDTH) {
            dx *= -1
        } else if (x + (dx * speed) < 0) {
            dx *= -1
        }

        if (y >= Game.globals.HEIGHT) {
            println("Ponto do inimigo")
            Game.restart()
            return
        } else if (y < 0) {
            println("Ponto do Jogador")
            Game.restart()
            return
        }

        val bounds: Rectangle = Rectangle((x + (dx * speed)).toInt(), (y + (dy * speed)).toInt(), width, height)
        val boundsPlayer: Rectangle = Rectangle(Game.globals.player.x.toInt(), Game.globals.player.y.toInt(), Game.globals.player.width, Game.globals.player.height)
        val boundsEnemy: Rectangle = Rectangle(Game.globals.enemy.x.toInt(), Game.globals.enemy.y.toInt(), Game.globals.enemy.width, Game.globals.enemy.height)

        if (bounds.intersects(boundsPlayer)) {
            val angle: Int = Random().nextInt(120 - 45) + 45 + 1
            dx = Math.cos(Math.toRadians(angle.toDouble()))
            dy = Math.sin(Math.toRadians(angle.toDouble()))
            if (dy > 0)dy *= -1
        }
        else if (bounds.intersects(boundsEnemy)) {
            val angle: Int = Random().nextInt(120 - 45) + 45 + 1
            dx = Math.cos(Math.toRadians(angle.toDouble()))
            dy = Math.sin(Math.toRadians(angle.toDouble()))
            if (dy < 0) dy *= -1
        }
    }

    fun render(g: Graphics) {
        g.color = Color.yellow
        g.fillRect(x.toInt(), y.toInt(), width, height)
    }
}