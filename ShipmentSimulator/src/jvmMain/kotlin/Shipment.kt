import kotlin.properties.Delegates

class Shipment {
    var status: String? = null
    var id: String? = null
    var createdTimestamp: Long? = null
    var notes =  ArrayList<String>()
        private set
    var updateHistory = ArrayList<ShippingUpdater>()
        private set
    var expectedDeliveryDateTimestamp: Long? = null
    var currentLocation: String? = null
    private var trackers = ArrayList<TrackerViewHelper>()

    fun update(update: ShippingUpdater) {
        updateHistory.add(update)
        status = update.newStatus
        createdTimestamp = update.timestamp
    }

}