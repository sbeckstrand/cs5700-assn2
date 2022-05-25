import kotlin.properties.Delegates

class Shipment {
    lateinit var status: String
    lateinit var id: String
    lateinit var notes: ArrayList<String>
        private set
    lateinit var updateHistory: ArrayList<ShippingUpdate>
        private set
    var expectedDeliveryDateTimestamp by Delegates.notNull<Long>()
    lateinit var currentLocation: String
    private lateinit var trackers: ArrayList<TrackerViewHelper>


}