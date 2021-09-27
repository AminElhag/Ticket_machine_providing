package sd.lemon.ticketmachineproviding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import sd.lemon.ticketmachineproviding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            EventBus.getDefault().post(
                Meal("Single Happy mel")
            )
        }

        binding.button2.setOnClickListener {
            EventBus.getDefault().post(
                Meal("Double Happy Meal")
            )
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        //Machine
        TicketMachine.onStart()
        TicketScreen.onStart()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        //Machine
        TicketMachine.onStop()
        TicketScreen.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onOrderReady(meal: FinishedMeal) {
        Toast.makeText(
            this,
            "Meal ready for ticket number: ${meal.meal.ticket} at ${meal.deliveredTime}",
            Toast.LENGTH_LONG
        ).show()
    }
}