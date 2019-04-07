package com.tcp.mozzi.back.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.mozzi.back.dto.user.CheckUserRequestDto;
import com.tcp.mozzi.back.dto.user.RegisterUserRequestDto;
import com.tcp.mozzi.back.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void registerUserOne() throws Exception {
        RegisterUserRequestDto dto = new RegisterUserRequestDto();
        dto.setName("김현욱");
        dto.setPassword("password");
        dto.setNickname("KUvH");
        dto.setStudentNumber("16100000");
        dto.setPhoneNumber("010-1234-5678");
        dto.setEmail("kuvh@live.co.kr");
        dto.setBirthday("1997-02-01");

        doNothing().when(userService).addUser(dto.toEntity());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void registerUserWhenInvalidEmail() throws Exception {
        RegisterUserRequestDto dto = new RegisterUserRequestDto();
        dto.setName("김현욱");
        dto.setPassword("password");
        dto.setNickname("KUvH");
        dto.setStudentNumber("16100000");
        dto.setPhoneNumber("010-1234-5678");
        dto.setEmail("kuvhlive.co.kr");
        dto.setBirthday("1997-02-01");

        doNothing().when(userService).addUser(dto.toEntity());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerUserWhenEmptyName() throws Exception {
        RegisterUserRequestDto dto = new RegisterUserRequestDto();
        dto.setPassword("password");
        dto.setNickname("KUvH");
        dto.setStudentNumber("16100000");
        dto.setPhoneNumber("010-1234-5678");
        dto.setEmail("kuvhlive.co.kr");
        dto.setBirthday("1997-02-01");

        doNothing().when(userService).addUser(dto.toEntity());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkUsernameWhenExist() throws Exception {
        CheckUserRequestDto dto = new CheckUserRequestDto("kuvh");

        when(userService.isExistUserByName("kuvh")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/user/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.useable", is(false)));
    }

    @Test
    public void checkUsernameWhenNotExist() throws Exception {
        CheckUserRequestDto dto = new CheckUserRequestDto("kuvh");

        when(userService.isExistUserByName("kuvh")).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.put("/user/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.useable", is(true)));
    }
}
