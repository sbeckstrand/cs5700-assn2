class LocationUpdater(simulator: TrackingSimulator) : UpdateStrategy() {
    private val simulator = simulator

    override fun buildUpdate(updateString: String) {
        val id: String
        val location: String

        val splitUpdate = updateString.split(",")

        try {
            id = splitUpdate[1]
            location = splitUpdate[3]
        } catch (e: Exception) {
            throw Exception("Invalid update input. Location type should have format of: location,<id>,<timestamp>,<location>")
        }

        if (simulator.findShipment(id) == null) {
            throw Exception("Shipment does not exist!")
        } else {
            val shipment = simulator.findShipment(id)
            val update = ShippingUpdate(shipment?.status, "location",  null, location)
            shipment?.update(update)
        }

    }

}