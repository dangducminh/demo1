package com.example.demo.Service;

import com.example.demo.DTO.ConvertBetweenUserAndUserDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInfoId() {
        User user = new User();
        user.setName("abc");
        UserDTO userDTO = new UserDTO();
        when(userRepository.getById(ArgumentMatchers.anyInt())).thenReturn(user);

        try(MockedStatic<ConvertBetweenUserAndUserDTO> mockedStatic = Mockito.mockStatic(ConvertBetweenUserAndUserDTO.class)){
            mockedStatic.when(()->ConvertBetweenUserAndUserDTO.ConvertUserToUserDTO(user)).thenReturn(userDTO);
        }

            UserDTO result = userService.getInfoId(1);
        assertEquals(result.getName(),"abc");
    }
}