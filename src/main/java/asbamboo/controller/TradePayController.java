package asbamboo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import asbamboo.Configure;
import asbamboo.java.sdk.model.TradePayRequest;
import asbamboo.java.sdk.model.TradePayResponse;
import asbamboo.util.IpUtil;

@Controller
public class TradePayController {

    @GetMapping("/trade/pay")
    public String form(Model model)
    {
        model.addAttribute("channels", Configure.channels);
        return "trade/pay/form";
    }
    
    @PostMapping("/trade/pay-process")
    public String process(
    	@RequestParam(name="channel", required=true, defaultValue="ALIPAY_PC") String channel,
    	@RequestParam(name="title", required=false, defaultValue="PAY-TEST") String title,
    	@RequestParam(name="total_fee", required=true, defaultValue="1") Integer total_fee,
    	@RequestParam(name="third_part", required=false, defaultValue="{}") String third_part,
		HttpServletRequest request,
    	Model model
    ){
    	Date now				= new Date();
    	SimpleDateFormat sdf	= new SimpleDateFormat("yyyymmddHHmmssSSS");
    	String out_trade_no		= sdf.format(now);
    	String scheme			= request.getScheme();
    	String host				= request.getServerName();
    	String port				= request.getServerPort() == 80 || request.getRemotePort() == 443 ? "" : ":" + request.getServerPort();
    	String notify_url		= scheme.concat("://").concat(host).concat(port).concat("/trade/pay-notify/").concat(channel).toString(); 
    	String return_url		= scheme.concat("://").concat(host).concat(port).concat("/trade/pay-return/").concat(channel).toString();

    	TradePayRequest trade_pay_request = new TradePayRequest();
    	trade_pay_request.setChannel(channel);
    	trade_pay_request.setTitle(title);
    	trade_pay_request.setClientIp(IpUtil.getIpAddr(request));
    	trade_pay_request.setNotifyUrl(notify_url);
    	trade_pay_request.setReturnUrl(return_url);
    	trade_pay_request.setOutTradeNo(out_trade_no);
    	trade_pay_request.setThirdPart(third_part);
    	trade_pay_request.setTotalFee(total_fee);
    	
    	try{
	    	if(channel.equals("ALIPAY_QRCD") || channel.equals("WXPAY_QRCD")){
	    		return this.qrcd(trade_pay_request, model);
	    	}
    	}catch(Exception e) {
    		model.addAttribute("error_string", e.toString());
    	}
    	return "trade/pay/form";
    }
    
    private String qrcd(TradePayRequest trade_pay_request, Model model) throws Exception
    {
		TradePayResponse response	= (TradePayResponse) trade_pay_request.post();
		model.addAttribute("pay_response", response);		
    	return "trade/pay/qrcd";
    }
    
    @PostMapping("/trade/pay-notify/{channel}")
    public String notity()
    {
    	return "200";
    }
    
    @RequestMapping("/trade/pay-return/{return}")
    public String returnAction()
    {
    	return "";
    }
}
