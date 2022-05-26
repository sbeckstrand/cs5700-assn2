import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Reader(lines: MutableList<String>, trackingSimulator: TrackingSimulator) {
    private val lines = lines;
    private val simulator = trackingSimulator

    fun runReader() {
        val scope = MainScope()

        scope.launch{
            lines.forEach{


                when (it.split(",")[0]) {
                    "created" -> {
                        CreatedUpdater(simulator).buildUpdate(it)
                    }
                    "shipped" -> {
                        ShippedUpdater(simulator).buildUpdate(it)
                    }
                    "location" -> {
                        LocationUpdater(simulator).buildUpdate(it)
                    }
                    "delayed" -> {
                        DelayedUpdater(simulator).buildUpdate(it)
                    }
                    "noteadded" -> {
                        NoteUpdater(simulator).buildUpdate(it)
                    }
                    "lost" -> {
                        LostUpdater(simulator).buildUpdate(it)
                    }
                    "canceled" -> {
                        CanceledUpdater(simulator).buildUpdate(it)
                    }
                    "delivered" -> {
                        DeliveredUpdater(simulator).buildUpdate(it)
                    }

                }
                simulator.debugShipments()
                delay(1000)
            }
            scope.cancel()
        }
    }

}