package sd.lemon.ticketmachineproviding

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

object TicketMachine {
    var currentTicket = 0
        private set

    fun onStart() {
        EventBus.getDefault().register(this)
    }

    fun onStop() {
        EventBus.getDefault().unregister(this)
    }

    private fun getNextTicket() {
        currentTicket++
    }

    @Subscribe
    fun onNewCustomer(meal: Meal) {
        getNextTicket()
        val detailedMeal = attachTicketToMeal(meal)
        askToPrepareMeal(detailedMeal)
    }

    private fun attachTicketToMeal(meal: Meal): DetailedMeal {
        return DetailedMeal(
            meal, Ticker(currentTicket, Calendar.getInstance().time)
        )
    }

    private fun askToPrepareMeal(meal: DetailedMeal) {
        EventBus.getDefault().post(meal)
    }
}