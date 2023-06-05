package furhatos.app.project.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.app.project.nlu.*
import furhatos.gestures.BasicParams
import furhatos.gestures.Gestures
import furhatos.gestures.defineGesture
import furhatos.util.Gender
import furhatos.util.Language


var techTopic : Boolean =false
var watchesSports : Boolean = false
val robotName = "Furhat Robot"

val Start : State = state(Parent) {

    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        furhat.voice = PollyNeuralVoice.Joey()
        furhat.setTexture("Ted")
        random(
            {furhat.say("Hello! I'm $robotName")},
            {furhat.say("Hi, my name is $robotName")}
        )
        furhat.ask("Have you met any robot before?")
    }


    onReentry{
        furhat.ask("Have you met any robot before?")
    }

    onResponse<Yes>{
        techTopic = true
        furhat.gesture(Gestures.BigSmile(strength = 2.0))
        furhat.say("That's great! I have met other humans before")
        furhat.say("However, I'm probably a bit different than the robot you might have interacted with. I'm a social robot")
        furhat.gesture(Gestures.Blink)
        furhat.say("This means that i'm designed to interact with humans in a way as similar as possible to human interaction. "
                + " I'm also designed to look like one of you, with different voices and faces. Look!")
        furhat.gesture(Gestures.Roll)
        goto(changeSkin)

    }
    //any topic you want > things
    onResponse<No>{
        furhat.say("Oh! ")
        furhat.gesture(Gestures.Oh(duration = 2.0 , strength = 2.0))
        furhat.say("Well, I'm pleased to be the very first. However, I'm a bit different than normal robots. I'm a social robot")
        furhat.say("This means that i'm designed to interact with humans in a way as similar to human interactions as possible. "
                + "You can talk to me about any topic you want. I would love to hear how was your day and provide support. Or you could tell me about your friends and family.")
        furhat.gesture(Gestures.Thoughtful(duration = 2.0 , strength = 2.0))
        furhat.say("I know during these times, interacting with people can be difficult. But hey!")
        furhat.gesture(Gestures.Smile)
        furhat.say("I'm here and I am an amazing company. Look, i can even change my appearance!")
        furhat.gesture(Gestures.Roll)
        goto(changeSkin)

    }
}

val changeSkin : State = state(Parent) {

    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE) //Change for neutral
        furhat.voice = PollyNeuralVoice.Amy()
        furhat.setTexture("Ursula")
        furhat.gesture(Gestures.Surprise)
        delay(1200)
        furhat.ask("So, what do you think?")
    }
    onReentry {
        furhat.ask("so what do you think about my look?")
    }
    onResponse<RemindOf>{
        var text: String
        furhat.gesture(Gestures.Blink)
        furhat.gesture(Gestures.Oh(duration = 2.0 , strength = 2.0))
        goto(Remind)


    }
    onResponse<No>{
        furhat.gesture(Gestures.ExpressSad(duration=3.0, strength = 1.0))
        furhat.say("Oh. Maybe you're right")
        goto(Appearance)
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.BigSmile(duration=3.0, strength = 1.0))
        furhat.say("Well? that is great!")
        goto(Appearance)
    }
    onResponse<Agreement>{
        raise(Yes())
    }

}
val Remind : State = state(Parent) {
    var text : String
    onEntry {
        furhat.ask("Wait, what did you say I look like?")

    }
    onResponse{
        text = it.text
        furhat.say("Oh, you said $text...Hmmm it doesnt ring a bell But I hope it's a nice comparison")
        furhat.say("But I think I could look better!")
        goto(Appearance)
    }

}


val Appearance = state(Parent){
    onEntry {
        furhat.say {
            +Gestures.BigSmile
            +"You know? I really like to have some hair!  I'd like to have arms and legs too, just like you!"
            +Gestures.Wink
            +delay(1000)
            +"I need arms and legs to do sports, I would like to learn to ski or bike someday. They looks like lots of FUN!"
            +delay(500)
            +"Not swimming though"
            +Gestures.ExpressFear(duration = 1.5, strength = 1.0)
            +"I hate WATER!"
            +Gestures.BrowFrown
            +Gestures.Blink
            +delay(500)
            +Gestures.CloseEyes
            +"AH! OUCH!"
            +Gestures.ExpressDisgust(duration = 2.0, strength = 1.0)
            +delay(2500)
            +Gestures.OpenEyes
            +"Oh sorry about that."
            +"I have PTSD from that time that evil little girl dropped me in water"
            +Gestures.ExpressFear
            +delay(1000)
            +"and now, everytime I hear that word, I go into shock, like you just saw"
            +Gestures.BigSmile
            +delay(1500)
            +"Anyway, for now, I am happy with watching other people swim, like on TV in swimming competitions."
            +Gestures.Smile
            +delay(800)
            +"We should watch sports together on TV sometime!"
        }
        furhat.ask("Do you watch any sports too?", timeout = 12000)
    }
    onReentry {
        furhat.ask("Don't you watch any sports then?")
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.BigSmile(duration=1.0, strength = 1.0))
        watchesSports = true
        goto(WatchSports)
    }
    onResponse<No>{

        furhat.gesture(Gestures.ExpressSad(duration=3.0, strength = 1.0))
        furhat.say("Oh, really?")
        watchesSports = false
        goto(WatchSports)
    }
    //Add onNoResponse
}

val WatchSports = state(Parent){
    onEntry {
        if(watchesSports == false){
            goto(hobbies)

        }
        else
            furhat.ask("Cool, what kind of sports do you watch?")

    }
    onResponse<Sports>{
        furhat.say("Oh that is fun! I wish I could play that too instead of just watch. But for obvious reasons, I cant")
        furhat.gesture(Gestures.BigSmile)
        goto(acquaintances)
    }
}

val acquaintances = state(Parent) {
    onEntry {
        furhat.ask("do you like to watch sports with some friend or relative?")

    }
    onResponse<No>{
        furhat.say("Well, then I would love to be your friend and watch sports together!")
        goto(end)
    }
    onResponse<Family>{
        furhat.say("Hearing of family members doing things together melts my CPU.")
        furhat.say("I would love to hear about all of your other family members too!")
        goto(end)
    }

    onResponse<Friends>{
        furhat.say("I bet you have a great time together, you should introduce them to me sometime")
        goto(end)
    }
}

val hobbies = state(Parent){
    onEntry {
        furhat.say("Okay, no sports. But what about other things")
        furhat.ask("What hobbies do you have?")
    }

    onResponse{
        furhat.say("Wow, I envy you humans. You are very lucky of having a body to enjoy such fun things.")
        goto(end)
    }
}
val end = state(Parent){
    onEntry {
        furhat.say("However, I'm afraid this conversation has been a bit to long for me and my battery is almost over. But I would love to talk to you any other time")
        furhat.gesture(Gestures.BigSmile)
        furhat.ask("What do you want to talk about next time? We could talk about friends, family, food, interests, movies or traveling", 12000)
    }
    onResponse<Next>{
        furhat.say {
            +"Yes. Sounds fun. I'm looking forward to that."
            +delay(500)
        }
        goto(endQ)
    }
    onResponse<No> {
        furhat.say{
            +"Please reconsider. I really want to talk to you again!"
            +delay(500)
        }
        goto(endQ)
    }
    onResponse{
        furhat.say {
            +"Yes. Sounds fun. I'm looking forward to that."
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
            + "That's great!"
            + delay(300)
            + "Let's talk again sometimes. I can't wait."
            + delay(300)
            + "Goodbye!"
        }
    }
    onResponse<No>{
        furhat.say {
            + "I'm sorry. I will do better next time."
            + delay(300)
            + "Goodbye!"
        }
    }

}