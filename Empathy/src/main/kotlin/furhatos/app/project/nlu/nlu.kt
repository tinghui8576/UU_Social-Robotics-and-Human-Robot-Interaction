package furhatos.app.project.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class RemindOf : Intent(){

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "You look like",
            "You remind me of",
            "You are like",
            "You're like"
        )
    }
}

class Agreement : Intent(){

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "I guess",
            "It is fine",
            "Not bad",
            "I like it",
            "this one is better",
            "it's amazing",
            "I love it",
            "it's incredible",
            "it's great",
            "this is better",
            "great",
            "good"
        )
    }
}

class Next : Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "friends", "family", "food", "interests", "movies", "traveling", "friend",
            "interest", "movie", "travel"
        )
    }
}

class Sports : Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "soccer", "basketball", "tennis", "baseball", "golf", "running", "volleyball",
            "badminton", "swimming", "dancing", "boxing", "table tennis", "skiing",
            "ice skating", "roller skating", "cricket", "rugby", "pool", "darts",
            "football", "bowling", "ice hockey", "surfing", "karate", "horse racing",
            "snowboarding", "skateboarding", "cycling", "ping pong", "archery", "fishing",
            "gymnastics", "figure skating", "rock climbing", "sumo wrestling", "taekwondo",
            "fencing", "water skiing", "jet skiing", "weight lifting", "scuba diving",
            "judo", "wind surfing", "kickboxing", "sky diving", "hang gliding", "bungee jumping",
            "breakdancing", "cheerleading", "competitive dancing", "dancesport", "freerunning",
            "gymnastics", "high kick", "parkour", "pole sports", "stunt", "trampolining",
            "hockey", "walking", "walk", "hiking"
        )

    }
}

class Family : Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "aunt", "brother", "boyfriend", "bride", "brother", "cousin", "dad", "daughter",
            "father", "father-in-law", "fiancé", "fiancée", "girlfriend", "godchild",
            "godfather", "godmother", "grandchild", "grandchildren", "granddaughter",
            "grandfather", "granddad", "grandpa", "grandmother", "grandma", "grandson",
            "great-grandparents", "groom", "husband", "mother", "mother-in-law", "mum",
            "mummy", "mom", "nephew", "niece", "parent", "parents","sister", "son",
            "stepbrother", "uncle", "wife", "relatives", "relative"
        )

    }
}

class Friends : Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "friend","friends", "mate", "colleague", "classmate", "workmate", "coworker","boss"
        )
    }
}