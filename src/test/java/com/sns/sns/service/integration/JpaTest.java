package com.sns.sns.service.integration;

import com.sns.sns.service.domain.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@DataJpaTest
public class JpaTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @WithMockUser
    @DisplayName("JPA N+1 발생 케이스")
    public void occurNPlusOne(){


    }

}
