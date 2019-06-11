package com.project.raluca.controller;

import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.service.UserDoctorService;
import com.project.raluca.service.UserService;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Named("userController")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDoctorService userDoctorService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public String login(){
//        UserDTO user = userService.findByUsernameAndPassword("test", "1234");
//
//        if(user!=null){
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user.getUsername());
//            return "dashboard.xhtml?faces-redirect=true";
//        }else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or Password is invalid"));
//        }
//
      return "dashboard.xhtml";
    }

    public UserDoctorDTO getUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return userDoctorService.findDoctorByUsername(authentication.getName());
    }
    public boolean isUserLoggedIn() {
//        String user = this.getUsername();
//        boolean result = !((user == null)|| user.isEmpty());
//        return result;
        return true;
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());

    }

//    public void redirect()
//    {
//        String redirectUrl = "dashboard.xhtml";
//
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+redirectUrl);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//    }


}
