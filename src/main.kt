import javax.swing.JFrame
import java.lang.Thread

fun main() {
    val frame = JFrame("Pong")
    frame.isResizable = false
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.add(Game)
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true

    Thread(Game).start()
}