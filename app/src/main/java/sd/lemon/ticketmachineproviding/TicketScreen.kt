package sd.lemon.ticketmachineproviding

import android.os.CountDownTimer
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

object TicketScreen {
    private const val AVERAGE_MEAL_TIME: Long = 10 * 1000 // 10 seconds

    private val order = LinkedList<DetailedMeal>()
    private var timer: CountDownTimer? = null

    fun onStart() {
        setTimerConditions()
        timer?.start()
        EventBus.getDefault().register(this)
    }

    fun onStop() {
        EventBus.getDefault().unregister(this)
        timer?.cancel()
    }

    private fun setTimerConditions() {
        timer = object : CountDownTimer(
            Long.MAX_VALUE, AVERAGE_MEAL_TIME
        ) {
            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
                val order = order.poll() ?: return
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }
    }

    private fun callCustomer(meal: DetailedMeal) {
        val finishedMeal = FinishedMeal(
            meal,
            Calendar.getInstance().time
        )
        EventBus.getDefault().post(finishedMeal)
    }

    @Subscribe
    fun onNewMeal(meal: DetailedMeal) {
        order.add(meal)
    }
}