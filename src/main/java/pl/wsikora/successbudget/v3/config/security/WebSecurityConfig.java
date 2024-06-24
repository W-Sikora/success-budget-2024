package pl.wsikora.successbudget.v3.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;


@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {

        return new SecurityEvaluationContextExtension();
    }
}
