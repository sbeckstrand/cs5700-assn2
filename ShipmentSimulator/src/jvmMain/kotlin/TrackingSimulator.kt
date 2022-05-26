import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

class TrackingSimulator(filePath: String) {
    private val file = filePath
    private var shipments = ArrayList<Shipment>()

    fun findShipment(shipmentId: String): Shipment? {
        shipments.forEach{
            if (shipmentId == it.id) {
                return it;
            }
        }
        return null
    }

    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }

    fun runSimulator() {
        val inputStream: InputStream = File(file).inputStream()
        val lines = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine { lines.add(it)}

        val reader = Reader(lines, this)
        reader.runReader()

    }

    fun debugShipments() {
        shipments.forEach{
            println("ID: ${it.id}, Status: ${it.status}, Location: ${it.currentLocation}, Expected Delivery: ${it.expectedDeliveryDateTimestamp}, Created Time: ${it.createdTimestamp}")
        }
        println("---")
    }

}