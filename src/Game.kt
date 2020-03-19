import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import java.lang.Exception
import java.lang.Runnable

object Game: Canvas(), Runnable, KeyListener {
    var isRunning: Boolean = false
    private lateinit var thread: Thread
    var layer: BufferedImage

    init {
        requestFocus()
        println("comecou")
        layer = BufferedImage(globals.WIDTH, globals.HEIGHT, BufferedImage.TYPE_INT_RGB)
        preferredSize = Dimension(globals.WIDTH * globals.SCALE, globals.HEIGHT * globals.SCALE)
        addKeyListener(this)
        isRunning = true
    }

    fun restart() {
        globals.ball = Ball(100.0, globals.HEIGHT / 2.0 - 1)
        globals.player = Player(100.0, globals.HEIGHT - 10.0)
        globals.enemy = Enemy(100.0, 0.0)
    }

    object globals {
        @JvmStatic val WIDTH: Int = 160
        @JvmStatic val HEIGHT: Int = 120
        @JvmStatic val SCALE: Int = 3
        @JvmStatic var ball: Ball
        @JvmStatic var player: Player
        @JvmStatic var enemy: Enemy

        init {
            ball = Ball(100.0, HEIGHT / 2.0 - 1)
            player = Player(100.0, HEIGHT - 10.0)
            enemy = Enemy(100.0, 0.0)
        }
    }

    fun tick() {
        globals.player.tick()
        globals.enemy.tick()
        globals.ball.tick()
    }

    fun render() {
        val bs = this.bufferStrategy
        if (bs == null) {
            this.createBufferStrategy(3)
            return
        }

        var g = layer.graphics
        g.color = Color.black
        g.fillRect(0, 0, globals.WIDTH, globals.HEIGHT)

        globals.player.render(g)
        globals.enemy.render(g)
        globals.ball.render(g)

        g = bs.drawGraphics
        g.drawImage(layer, 0, 0, globals.WIDTH * globals.SCALE, globals.HEIGHT * globals.SCALE, null)
        bs.show()
    }

    override fun run() {
        while (isRunning) {
            tick()
            render()
            try {
                Thread.sleep(1000/60)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun keyTyped(p0: KeyEvent?) {
        TODO("Not yet implemented")
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_RIGHT) {
            globals.player.right = true
        } else if (e?.keyCode == KeyEvent.VK_LEFT) {
            globals.player.left = true
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_RIGHT) {
            globals.player.right = false
        } else if (e?.keyCode == KeyEvent.VK_LEFT) {
            globals.player.left = false
        }
    }
}