package asbamboo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asbamboo.java.sdk.model.TradeRefundRequest;
import asbamboo.java.sdk.model.TradeRefundResponse;

@Controller
public class TradeRefundController {

    @GetMapping("/trade/refund")
    public String form(Model model) {
        return "trade/refund/form";
    }

    @PostMapping("/trade/refund")
    public String query(
    	@RequestParam(name="in_trade_no", required=false, defaultValue="") String in_trade_no,
    	@RequestParam(name="out_trade_no", required=false, defaultValue="") String out_trade_no,
    	@RequestParam(name="out_refund_no", required=false, defaultValue="") String out_refund_no,
    	@RequestParam(name="refund_fee", required=false, defaultValue="") Integer refund_fee,
    	@RequestParam(name="third_part", required=false, defaultValue="") String third_part,
    	Model model
    ){

	    try{
	    	TradeRefundRequest trade_request		= new TradeRefundRequest();
	    	trade_request.setInTradeNo(in_trade_no);
	    	trade_request.setOutTradeNo(out_trade_no);
	    	trade_request.setOutRefundNo(out_refund_no);
	    	trade_request.setRefundFee(refund_fee);
	    	trade_request.setThirdPart(third_part);
	    	TradeRefundResponse trade_response	= (TradeRefundResponse) trade_request.post();
	    	
	    	model.addAttribute("trade_response", trade_response);
	    	
		}catch(Exception e) {
			model.addAttribute("error_string", e.toString());
		}

    	return "trade/refund/form";
    }
}
