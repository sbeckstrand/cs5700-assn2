class DelayedUpdate : UpdateStrategy() {

    override fun buildUpdate(updateString: String): ShippingUpdate {
        return ShippingUpdate("test", "test", 0.1.toLong())
    }

}