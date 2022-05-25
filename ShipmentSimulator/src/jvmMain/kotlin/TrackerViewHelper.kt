import java.util.Date

class TrackerViewHelper {
    lateinit var shipmentId: String
        private set
    lateinit var shipmentNotes: ArrayList<String>
        private set
    lateinit var expectedShipmentDeliveryDate: Date
        private set
    lateinit var shipmentStatus: String
        private set
}