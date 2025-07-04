/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginApp.LoginApp;


import LoginApp.LoginApp.DataUser;
import LoginApp.LoginApp.DataUserJpaController;
import LoginApp.LoginApp.exceptions.PreexistingEntityException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginApp_LoginApp_jar_0.0.1-SNAPSHOTPU");
    private final DataUserJpaController userController = new DataUserJpaController(emf);

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("dataUser", new DataUser());
        return "register";
    }
    
    
    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute DataUser dataUser, Model model) {
        try {
            userController.create(dataUser);
            return "redirect:/login";
        } catch (PreexistingEntityException e) {
            model.addAttribute("error", "User sudah ada!");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan saat registrasi!");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        List<DataUser> users = userController.findDataUserEntities();
        for (DataUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                session.setAttribute("loggedInUser", user);
                return "redirect:/dashboard";
            }
        }
        model.addAttribute("error", "Username atau password salah!");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        DataUser user = (DataUser) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            return "dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
