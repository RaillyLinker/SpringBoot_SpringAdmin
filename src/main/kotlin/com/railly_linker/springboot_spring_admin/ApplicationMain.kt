package com.railly_linker.springboot_spring_admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAdminServer
class SpringbootSpringAdminApplication

fun main(args: Array<String>) {
	runApplication<SpringbootSpringAdminApplication>(*args)
}
