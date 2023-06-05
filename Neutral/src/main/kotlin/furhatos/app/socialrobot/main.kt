package furhatos.app.socialrobot

import furhatos.app.socialrobot.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class SocialrobotSkill : Skill() {
    override fun start() {
        /* Initial State Idle */
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
