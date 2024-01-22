package app.wyq.learning_kotlin.controller

import app.wyq.learning_kotlin.model.Fruit
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/demo")
class DemoController {
    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
        return String.format("Hello %s!", name)
    }

    private var fruits = mutableListOf(
        Fruit(1, "Apple", 100.0),
        Fruit(2, "Banana", 36.9),
        Fruit(3, "Pear", 0.6),
        Fruit(4, "Peach", 1.1),
        Fruit(5, "Strawberry", 2.5)
    )

    @GetMapping("")
    fun getFruits() = fruits

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun postFruit(@RequestBody fruit: Fruit): Fruit {
        val maxId = fruits.map{it.id}.maxOrNull()?: 0
        val nextId = maxId + 1
        val newFruit = Fruit(id = nextId, name = fruit.name, floor_price = fruit.floor_price)
        fruits.add(newFruit)
        return newFruit
    }
}