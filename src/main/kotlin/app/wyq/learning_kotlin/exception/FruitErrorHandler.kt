package app.wyq.learning_kotlin.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class FruitErrorHandler {
    @ExceptionHandler(FruitNotFoundException::class)
    fun handleFruitNotFoundException(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<String> {
        return ResponseEntity("Fruit not Found", HttpStatus.NOT_FOUND)
    }
}