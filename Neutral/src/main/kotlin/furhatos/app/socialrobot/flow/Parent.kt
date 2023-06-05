package furhatos.app.socialrobot.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users

val Parent: State = state {

    onButton("START", color = Color.Blue){
        goto(Start)
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.glance(it)
        } else {
            goto(Idle)

        }
    }

    onResponse{
        furhat.say("Sorry, I didn't get that")
        reentry()
    }
    onNoResponse{
        furhat.ask("Did you say anything?")
        reentry()
    }
}