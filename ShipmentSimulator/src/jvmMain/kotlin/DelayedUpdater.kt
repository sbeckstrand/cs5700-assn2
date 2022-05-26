class DelayedUpdater(simulator: TrackingSimulator) : UpdateStrategy() {
    private val simulator = simulator

    override fun buildUpdate(updateString: String) {
        val id: String
        val estimatedDeliveryTimestamp: Long

        val splitUpdate = updateString.split(",")

        try {
            id = splitUpdate[1]
            estimatedDeliveryTimestamp = splitUpdate[3].toLong()
        } catch (e: Exception) {
            throw Exception("Invalid update input. Shipped type should have format of: delayed,<id>,<created timestamp>, <estimated delivery timestamp>")
        }

        if (simulator.findShipment(id) == null) {
            throw Exception("Shipment does not exist!")
        } else {
            val shipment = simulator.findShipment(id)
            val update = ShippingUpdate(shipment?.status, "delayed",  estimatedDeliveryTimestamp, null)
            shipment?.update(update)
        }
    }

}