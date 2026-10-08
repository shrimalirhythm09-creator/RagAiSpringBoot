package devPilot.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class CryptoConfig {

    @Value("${app.encryptor.password}")
    private String password;

    @Value("${app.encryptor.salt}")
    private String salt;

    @Bean
    public TextEncryptor tokenEncryptor() {
        return Encryptors.text(password, salt);
    }
}