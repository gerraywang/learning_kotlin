package app.wyq.learning_kotlin.controller

import app.wyq.learning_kotlin.model.Fruit
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo")
class DemoController {
    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
        return String.format("Hello %s!", name)
    }

    private var fruits = mutableListOf(
        Fruit(1, "Apple", 100.0),
        Fruit(1, "Banana", 36.9),
        Fruit(1, "Pear", 0.6),
        Fruit(1, "Peach", 1.1),
        Fruit(1, "Strawberry", 2.5)
    )

    @GetMapping("")
    fun getFruits() = fruits
}