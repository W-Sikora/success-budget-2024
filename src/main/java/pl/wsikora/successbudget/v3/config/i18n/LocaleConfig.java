package pl.wsikora.successbudget.v3.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.Duration;
import java.util.Locale;


@Configuration
class LocaleConfig implements WebMvcConfigurer {

    private static final Locale LOCALE_PL = new Locale("pl");
    static final Duration COOKIE_MAX_AGE = Duration.ofDays(14L);
    static final String LANGUAGE = "language";

    @Bean
    LocaleResolver localeResolver() {

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver(LANGUAGE);

        cookieLocaleResolver.setDefaultLocale(LOCALE_PL);
        cookieLocaleResolver.setCookieMaxAge(COOKIE_MAX_AGE);

        return cookieLocaleResolver;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();

        localeChangeInterceptor.setParamName(LANGUAGE);

        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());
    }
}
