package com.asi.lib;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.webservices.AuthWebService;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.mockito.Mockito.when;

public class AbstractLoginControllerTest {

    @MockBean
    private AuthWebService webService;

    public AbstractLoginControllerTest() {
        this.jwtToken = "fake token";
        this.currentUser = new UserDTO(1L, "admin lastname", "admin firstname", "admin@admin.fr", 500L);
    }

    private String jwtToken;
    private UserDTO currentUser;

    public MockHttpServletRequest authProcessRequest(MockHttpServletRequest request) {
        assert jwtToken != null;
        request.addHeader("Authorization", "Bearer ".concat(this.jwtToken));
        return request;
    }

    public void prepareAuthFilter() {
        Mockito.when(this.webService.getUser(this.jwtToken)).thenReturn(this.currentUser);
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
    }
}
