class Shipment(id: String, createdTimestamp: Long) {
    var status: String? = null
    var id = id
    var createdTimestamp = createdTimestamp
    var notes =  ArrayList<String>()
        private set
    var updateHistory = ArrayList<ShippingUpdate>()
        private set
    var expectedDeliveryDateTimestamp: Long? = null
    var currentLocation: String? = null
    private var trackers = ArrayList<TrackerViewHelper>()

    fun update(update: ShippingUpdate) {
        updateHistory.add(update)
        status = update.newStatus
        if (update.deliveryTimestamp != null) {
            expectedDeliveryDateTimestamp = update.deliveryTimestamp
        }
        if (update.location != null) {
            currentLocation = update.location
        }
        trackers.forEach{
            it.updateStatus()
        }

        // Some logic here to inform trackers
    }

    fun addNote(note: String) {
        notes.add(note)

        trackers.forEach{
            it.addNote(note)
        }
    }

}