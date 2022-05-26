import java.util.Date

class TrackerViewHelper(shipment: Shipment?) {

    var shipment = shipment;

    var id = shipment?.id
        private set

    var shipmentNotes = ArrayList<String>()
        private set
    var expectedShipmentDeliveryDate: Date = Date()
        private set
    var shipmentStatus: String? = null
        private set
    var updateHistory = ArrayList<ShippingUpdate>()
        private set

    fun addNote(note: String) {
        shipmentNotes.add(note)
    }

    fun updateStatus() {
        expectedShipmentDeliveryDate = Date() // Needs to be replaced with an updated version of shipment.date
        shipmentStatus = shipment?.status
        updateHistory = shipment?.updateHistory!!
    }

}