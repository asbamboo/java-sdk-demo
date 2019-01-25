package asbamboo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asbamboo.java.sdk.model.TradeCancelRequest;
import asbamboo.java.sdk.model.TradeCancelResponse;

@Controller
public class TradeCancelController {

    @GetMapping("/trade/cancel")
    public String form() {
        return "trade/cancel/form";
    }
    
    @PostMapping("/trade/cancel")
    public String query(
    	@RequestParam(name="in_trade_no", required=false, defaultValue="") String in_trade_no,
    	@RequestParam(name="out_trade_no", required=false, defaultValue="") String out_trade_no,
    	@RequestParam(name="third_part", required=false, defaultValue="") String third_part,
    	Model model
    ){

	    try{
	    	TradeCancelRequest trade_request		= new TradeCancelRequest();
	    	trade_request.setInTradeNo(in_trade_no);
	    	trade_request.setOutTradeNo(out_trade_no);
	    	trade_request.setThirdPart(third_part);
	    	TradeCancelResponse trade_response	= (TradeCancelResponse) trade_request.post();
	    	
	    	model.addAttribute("trade_response", trade_response);
	    	
		}catch(Exception e) {
			model.addAttribute("error_string", e.toString());
		}

    	return "trade/cancel/form";
    }
}
