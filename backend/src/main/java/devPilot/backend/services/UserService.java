package devPilot.backend.services;

import devPilot.backend.entity.Users;
import devPilot.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    public  final UserRepository userRepository;
    public  final TextEncryptor tokenEncryptor;
   
        // TODO Auto-generated method stub
     @Transactional
     public Users upsertFromGitHub(
        Map<String, Object> attributes,
        String accessToken,
        String scopes) {

     Long githubId = toLong(attributes.get("id"));

     String login = String.valueOf(attributes.get("login"));

      String name = attributes.get("name") != null
            ? String.valueOf(attributes.get("name"))
            : login;

    String avatarUrl = attributes.get("avatar_url") != null
            ? String.valueOf(attributes.get("avatar_url"))
            : null;

    // Encrypt GitHub access token before storing
    String encryptedToken = tokenEncryptor.encrypt(accessToken);

    // Find existing user or create a new one
    Users user = userRepository
            .findByGithubId(githubId)
            .orElseGet(Users::new);

    // Update user information
    user.setGithubId(githubId);
    user.setGithubUsername(login);
    user.setDisplayName(name);
    user.setAvatarUrl(avatarUrl);
    user.setAccessToken(encryptedToken);
    user.setTokenScope(scopes);

    return userRepository.save(user);
}

    @Transactional
    public Users requiredById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    }

    public String decryptAccessToken(Users user) {
        return tokenEncryptor.decrypt(user.getAccessToken());
    }

    private static Long toLong(Object value) {
        if (value instanceof Number number) {
            return number.longValue();
        }

        return Long.parseLong(String.valueOf(value));
    }

    
}

