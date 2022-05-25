abstract class UpdateStrategy {
    abstract fun buildUpdate(updateString: String): ShippingUpdate
}