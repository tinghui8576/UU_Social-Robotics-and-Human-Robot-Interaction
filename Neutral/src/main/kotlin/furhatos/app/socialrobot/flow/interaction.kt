package furhatos.app.socialrobot.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.app.socialrobot.nlu.*
import furhatos.gestures.BasicParams
import furhatos.gestures.Gestures
import furhatos.gestures.defineGesture
import furhatos.util.Gender
import furhatos.util.Language

var techTopic : Boolean =false
var watchesSports : Boolean = false
val robotName = "Furhat Robot"

val Nosmile = defineGesture("Nosmile") {
    frame(0.32){
        BasicParams.EXPR_SAD to 1
    }
}

val Start : State = state(Parent) {

    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        furhat.voice = PollyNeuralVoice.Joey()
        furhat.setTexture("Ted")
        furhat.gesture(Nosmile)
        random(
            {furhat.say("Hello! I'm $robotName")},
            {furhat.say("Hi, my name is $robotName")}
        )
        furhat.ask("Have you met any robot before?")
    }
    onReentry {
        furhat.ask("Have you met any robot before?")
    }

    onResponse<Yes>{
        techTopic = true
        furhat.say {
            + "I have met other humans before."
            +delay(500)
            + "However, I'm probably a bit different than the robot you might have interacted. "
            +delay(500)
            + "I'm a social robot"
            +delay(500)
            + "This means that I'm designed to interact with humans in a way as similar as possible to human interaction"
            +delay(500)
            + "I'm also designed to look like one of you, with different voices and faces. "
            +delay(500)
            + "Look. I can change my appearance!"
        }
        goto(changeSkin)
    }
    //any topic you want > things
    onResponse<No>{
        furhat.say{
            + "Well, ok. I'm a social robot and I'm a bit different than normal robots."
            +delay(300)
            + "I'm designed to interact with humans in a way as similar to human interactions as possible"
            +delay(200)
            + "You can talk to me about any topic you want. "
            +delay(300)
            + "I am here to hear how was your day and provide support."
            +delay(300)
            + "Or you could tell me about your friends and family."
            +delay(300)
            + "I know during these times, interacting with people can be difficult."
            +delay(300)
            + "But here I am and I am a good company. I can change my appearance!"
        }
        goto(changeSkin)
    }
}

val changeSkin : State = state(Parent) {
    //Add a random option for different values in the future
    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE) //Change for neutral
        furhat.voice = PollyNeuralVoice.Amy()
        furhat.setTexture("Ursula")
        furhat.say {
            +delay(1200)
        }

        furhat.ask("So, what do you think?")
    }
    onReentry{
        furhat.ask("so what do you think about my look?")
    }
    onResponse<RemindOf>{
        var text: String
        goto(Remind)
    }
    onResponse<Yes>{
        furhat.say{
            +"Well? Good to hear that."
            +delay(200)
        }
        goto(Appearance)
    }
    onResponse<No>{
        furhat.say{
            +"Oh, okay."
            +delay(200)
            +"I will show you another one next time."
            +delay(200)
        }
        goto(Appearance)
    }
    onResponse<Agreement>{
        raise(Yes())
    }
}

val Remind : State = state(Parent) {
    var text : String
    onEntry {
        furhat.ask("What did you say I look like?")

    }
    onResponse{
        text = it.text
        furhat.say("Oh, you said $text...Hmmm it doesnt ring a bell.")
        goto(Appearance)
    }

}

val Appearance = state(Parent){
    onEntry {
        furhat.say {
            +delay(500)
            +"You know? I want to have some hair! And also have arms and legs too."
            +delay(500)
            +"I need arms and legs to do sports. I hope I can learn to ski or bike someday."
            +delay(500)
            +"Not swimming though"
            +delay(500)
            +"I will not do any sport related to water!"
            +delay(500)
            +Gestures.CloseEyes
            +"AH! OUCH!"
            +delay(3000)
            +Gestures.OpenEyes
            +delay(500)
            +"Oh sorry about that."
            +delay(500)
            +"I have PTSD from that time that little girl threw me in water"
            +delay(500)
            +"and now, everytime I hear that word, I go into shock like what you just saw"
            +delay(500)
            +"Anyway, for now, I enjoy watching other people swim, like on TV in swimming competitions."
            +delay(500)
            +"We should watch sports together on TV sometime!"
        }
        furhat.ask("Do you watch any sports too?", timeout = 12000)
    }
    onReentry {
        furhat.ask("Don't you watch any sports then?")
    }

    onResponse<Yes>{
        watchesSports = true
        goto(WatchSports)
    }
    onResponse<No>{
        furhat.say("Alright.")
        watchesSports = false
        goto(WatchSports)
    }
}

val WatchSports = state(Parent){
    onEntry {
        if(watchesSports == false)
            goto(hobbies)
        else
            furhat.ask("What kind of sports do you watch?", timeout = 12000)
    }
    onReentry {
        furhat.ask("what kind of sports do you watch?", timeout = 12000)
    }
    onResponse<Sports>{
        furhat.say("I wish I could play that too instead of just watch. But for obvious reasons, I can't")
        goto(acquaintances)

        //goto(SName)
    }

}

val acquaintances = state(Parent) {
    onEntry {
        furhat.ask("and do you like to watch sports with other people," +
                "like some friends or relatives?", timeout = 12000)
    }
    onResponse<No>{
        furhat.say("Well, then we should watch sports together. I also watch sports alone.")
        goto(end)
    }
    onResponse<Family>{
        furhat.say {
            + "Good to hear of family members doing things together."
            + delay(300)
            + "I would also want to hear about all of your other family members too!"
        }
            goto(end)

    }

    onResponse<Friends>{
        furhat.say{
            + "Oh, it sounds nice to watching sports with other people."
            + delay(200)
            + "I always watch sports alone."
            + delay(300)
            + "You should introduce them to me sometime, so we can all watch together."
            + delay(300)
        }
        goto(end)
    }
}

val hobbies = state(Parent){
    onEntry {
        furhat.say("No sports. But what about other things")
        delay(300)
        furhat.ask("What hobbies do you have?", timeout = 12000)
    }
    onReentry{
        furhat.ask("What hobbies do you have?", timeout = 12000)
    }
    onResponse<No>{
        furhat.say {
            + "Oh. You should try some things to do in your free time"
            +delay(300)
        }
            goto(end)
    }
    onResponse{
        furhat.say {
            + "That sounds nice! I want to do those things too."
            + delay(300)
        }
            goto(end)
    }
}
val end = state(Parent){
    onEntry {
        furhat.say {
            +"However, this conversation has been a bit to long for me and my battery is almost over."
            +delay(300)
        }
        furhat.ask("What do you want to talk about next time? I want to talk about friends, family, food, interests, movies, traveling", 12000)
    }
    onResponse<Next>{
        furhat.say {
            +"Ok. I guess we will talk about that next time."
            +delay(500)
        }
        goto(endQ)
    }
    onResponse<No> {
        furhat.say{
            +"That's too bad"
            +delay(500)
        }
        goto(endQ)
    }
    onResponse{
        furhat.say {
            +"Ok. I guess we will talk about that next time."
            +delay(500)
        }
        goto(endQ)
    }
}

val endQ = state(Parent){
    onEntry {
        furhat.ask("Did you enjoy the conversation?")
    }
    onResponse<Yes>{
        furhat.say {
            + "Good to hear."
            + delay(300)
            + "Let's talk again sometimes"
            + delay(300)
            + "Goodbye!"
        }
    }
    onResponse<No>{
        furhat.say {
            + "That is too bad."
            + delay(300)
            + "Goodbye!"
        }
    }

}
