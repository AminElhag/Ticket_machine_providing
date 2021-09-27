package sd.lemon.ticketmachineproviding

import java.util.Date

data class FinishedMeal(
    val meal: DetailedMeal,
    val deliveredTime: Date
)
