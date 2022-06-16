package net.yolopix.moneyz.model

/**
 * This enum represents a type of expense in the zero based budget method
 * @param stepNumber Used to order each step when making previsions
 */
enum class ExpenseType(val stepNumber: Int) {
    BILLS(1),
    ENVELOPES(2),
    SINKING_FUNDS(3),
    EXTRA_DEBT(4),
    EXTRA_SAVINGS(5);

    companion object {
        fun getTypeFromInt(stepNumber: Int): ExpenseType? {
            for (value in values()) {
                if (value.stepNumber == stepNumber)
                    return value
            }
            return null
        }
    }
}