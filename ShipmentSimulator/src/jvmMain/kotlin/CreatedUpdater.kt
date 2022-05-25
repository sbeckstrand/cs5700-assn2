

class CreatedUpdater(simulator: TrackingSimulator) : UpdateStrategy() {
    private val simulator = simulator

    override fun buildUpdate(updateString: String) {
        val id: String
        val createdTimestamp: Long

        val splitUpdate = updateString.split(",")

        try {
            id = splitUpdate[1]
            createdTimestamp = splitUpdate[2].toLong()
        } catch (e: Exception) {
            throw Exception("Invalid update input. Created type should have format of: created,<id>,<timestamp>")
        }

        val shipment = Shipment()
        if (simulator.findShipment(id) != null) {
            throw Exception("Cannot create Shipment with the same ID as an existing Shipment.")
        } else {
            val update = ShippingUpdater(null, "created", createdTimestamp)
            shipment.id = id
            shipment.update(update)
            simulator.addShipment(shipment)
        }

    }

}