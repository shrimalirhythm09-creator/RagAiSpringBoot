package devPilot.backend.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Users {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    private Long githubId;

    @Column(nullable = false,length = 100)
    private String githubUsername;

    @Column(nullable = false,length = 500)
    private String displayName;

    @Column(length = 600)
    private String avatarUrl;


    @Column(nullable = false,columnDefinition = "Text")
    private String accessToken;

    @Column(length = 500)
    private String tokenScope;

    @Column(nullable = false,updatable = false)
    private  Instant createdAt;

    @PrePersist 
    void oncreate(){
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }



}
