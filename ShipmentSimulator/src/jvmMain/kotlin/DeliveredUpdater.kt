class DeliveredUpdater(simulator: TrackingSimulator) : UpdateStrategy() {
    private val simulator = simulator

    override fun buildUpdate(updateString: String) {
        val id: String

        val splitUpdate = updateString.split(",")

        try {
            id = splitUpdate[1]
        } catch (e: Exception) {
            throw Exception("Invalid update input. Delivered type should have format of: delivered,<id>,<timestamp>")
        }

        if (simulator.findShipment(id) == null) {
            throw Exception("Shipment does not exist!")
        } else {
            val shipment = simulator.findShipment(id)
            val update = ShippingUpdate(shipment?.status, "delivered",  null, null)
            shipment?.update(update)
        }
    }

}