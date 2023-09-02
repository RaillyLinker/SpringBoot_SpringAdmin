package com.railly_linker.springboot_spring_admin


import de.codecentric.boot.admin.server.config.AdminServerProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


// (서비스 보안 시큐리티 설정)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
    private val adminServer: AdminServerProperties
) {
    // <멤버 변수 공간>
    private var loggerMbr: Logger = LoggerFactory.getLogger(this::class.java)

    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    @Bean
    fun securityFilterChainAll(http: HttpSecurity): SecurityFilterChain {
        // (API 요청 제한)
        http.authorizeHttpRequests { authorizeHttpRequestsCustomizer ->
            // 인증 및 인가 조건 설정
            // 스웨거 열람은 관리자와 개발자만 가능
            authorizeHttpRequestsCustomizer.requestMatchers(this.adminServer.path("/assets/**")).permitAll()
            authorizeHttpRequestsCustomizer.requestMatchers(this.adminServer.path("/login")).permitAll()

            authorizeHttpRequestsCustomizer.anyRequest().authenticated()
        }

        // (form 로그인)
        http.formLogin {
            it.loginPage(this.adminServer.path("/login")).successHandler { _, response, authentication ->
                // 로그인 성공 후 핸들러
                println("authentication: " + authentication.name)
                response.sendRedirect("/")
            }
        }

        // (로그아웃)
        http.logout {
            it.logoutUrl(this.adminServer.path("/logout"))
        }

        // Spring Boot Admin 클라이언트를 등록하기 위해 HTTP-Basic 지원을 사용한다.
        http.httpBasic {

        }

        // 쿠키를 사용하여 CSRF 보호 기능 구현
        http.csrf {
            it.disable()
        }

        return http.build()
    }


    // ---------------------------------------------------------------------------------------------
    // <비공개 메소드 공간>


    // ---------------------------------------------------------------------------------------------
    // <중첩 클래스 공간>

}