package app.wyq.learning_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import java.util.UUID
import org.springframework.jdbc.core.query
import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Service

@SpringBootApplication
class LearningKotlinApplication

fun main(args: Array<String>) {
	runApplication<LearningKotlinApplication>(*args)
}

@RestController
class MessageController(val service: MessageService)  {
    @GetMapping("/")
    fun index(@RequestParam("name") name: String) = "Hello, $name!"

    @GetMapping("/id/")
    fun index1() = listOf(
            Message("1", "Hello!"),
            Message("2", "Bonjour!"),
            Message("3", "Privet!"),
    )

    @GetMapping("/db/")
    fun index(): List<Message> = service.findMessages()

    @GetMapping("/db/{id}")
    fun index1(@PathVariable id: String): List<Message> =
            service.findMessageById(id)

    @PostMapping("/db/")
    fun post(@RequestBody message: Message) {
        service.save(message)
    }
}

data class Message(val id: String?, val text: String)

@Service
class MessageService(val db: JdbcTemplate) {

    fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun findMessageById(id: String): List<Message> = db.query("select * from messages where id = ?", id) { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun save(message: Message) {
        val id = message.id ?: UUID.randomUUID().toString()
        db.update(
                "insert into messages values ( ?, ? )",
                id, message.text
        )
    }
}