package asbamboo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asbamboo.java.sdk.model.TradeQueryRequest;
import asbamboo.java.sdk.model.TradeQueryResponse;

@Controller
public class TradeQueryController {

    @GetMapping("/trade/query")
    public String form() {
        return "trade/query/form";
    }
    
    @PostMapping("/trade/query")
    public String query(
    	@RequestParam(name="in_trade_no", required=false, defaultValue="") String in_trade_no,
    	@RequestParam(name="out_trade_no", required=false, defaultValue="") String out_trade_no,
    	Model model
    ){

	    try{
	    	TradeQueryRequest query_request		= new TradeQueryRequest();
	    	query_request.setInTradeNo(in_trade_no);
	    	query_request.setOutTradeNo(out_trade_no);
	    	TradeQueryResponse query_response	= (TradeQueryResponse) query_request.post();
	    	
	    	model.addAttribute("query_response", query_response);
	    	
		}catch(Exception e) {
			model.addAttribute("error_string", e.toString());
		}

    	return "trade/query/form";
    }
}
