package src.main.java;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TradeQueryController {

    @GetMapping("/trade/query")
    public String form(Model model) {
//        model.addAttribute("name", name);
        return "trade/query/form";
    }

}
