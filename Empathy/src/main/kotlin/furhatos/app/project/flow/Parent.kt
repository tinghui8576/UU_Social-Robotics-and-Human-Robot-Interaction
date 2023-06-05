package furhatos.app.project.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users

val Parent: State = state {

    onButton("1START", color = Color.Blue){
        goto(Start)
    }
    onButton("2Appearance", color = Color.Yellow){
        goto(Appearance)
    }
    onButton("3WatchSports", color = Color.Yellow){
        goto(WatchSports)
    }
    onButton("4acquaintances", color = Color.Yellow){
        goto(acquaintances)
    }
    onButton("5hobbies", color = Color.Yellow){
        goto(hobbies)
    }
    onButton("6end", color = Color.Yellow){
        goto(end)
    }
    onButton("7endQ", color = Color.Yellow){
        goto(endQ)
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