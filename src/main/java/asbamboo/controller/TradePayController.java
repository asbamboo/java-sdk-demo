package asbamboo.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import asbamboo.Configure;
import asbamboo.java.sdk.Logger;
import asbamboo.java.sdk.Sign;
import asbamboo.java.sdk.model.TradePayRequest;
import asbamboo.java.sdk.model.TradePayResponse;
import asbamboo.java.sdk.notify.PayNotify;
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
		HttpServletResponse rep,
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
	    		return this.qrcd(trade_pay_request, model, rep);
	    	}else if(channel.equals("ALIPAY_PC") || channel.equals("WXPAY_H5")) {
	    		return this.submit(trade_pay_request, model, rep);
	    	}
    	}catch(Exception e) {
    		model.addAttribute("error_string", e.toString());
    	}
    	return "trade/pay/form";
    }
    
    private String qrcd(TradePayRequest trade_pay_request, Model model, HttpServletResponse rep) throws Exception
    {
		TradePayResponse response	= (TradePayResponse) trade_pay_request.post();
		model.addAttribute("pay_response", response);		
    	return "trade/pay/qrcd";
    }
    
    private String submit(TradePayRequest trade_pay_request, Model model, HttpServletResponse rep) throws Exception
    {
		PrintWriter out	= rep.getWriter();
    	try{
    		out.write(trade_pay_request.submit());
    		out.flush();
    	}finally{
    		out.close();
    	}
    	return "";
    }

    
    @ResponseBody
    @PostMapping("/trade/pay-notify/{channel}")
    public String notity(
    	@RequestParam(name="channel", required=true, defaultValue="") String channel,
		HttpServletRequest request
    ){
    	PayNotify	pn	= new PayNotify(request);
    	StringBuilder result	= new StringBuilder();

    	if(pn.checkSign()){
    		//你应该自定义你的应用收到支付通知时的逻辑
//    		String channel			= pn.getChannel();
//    		String in_trade_no		= pn.getInTradeNo();
//    		String out_trade_no		= pn.getOutTradeNo();
//    		String title			= pn.getTitle();
//    		String total_fee		= pn.getTotalFee();
//    		String client_ip		= pn.getClientIp();
//    		String trade_status		= pn.getTradeStatus();
//    		String payok_ymdhis		= pn.getPayokYmdhis();
//    		String payed_ymdhis		= pn.getPayedYmdhis();
//    		String cancel_ymdhis	= pn.getCancelYmdhis();
//    		String random			= pn.getRandom();
//    		String sign				= pn.getSign();
    		Enumeration<String> params	= request.getParameterNames();
        	while(params.hasMoreElements()){
        		String name			= params.nextElement();
        		String value		= request.getParameterValues(name)[0];
        		result.append(name + ":" + value + "\n");
        	}
    	}    	
    	Logger.info("Notify:" + result.toString());
    	return "SUCCESS";
    }
    
    @ResponseBody
    @RequestMapping("/trade/pay-return/{channel}")
    public String returnAction(
    	@RequestParam(name="channel", required=true, defaultValue="") String channel,
		HttpServletRequest request
    ){
    	PayNotify	pn	= new PayNotify(request);
    	StringBuilder result	= new StringBuilder();

    	if(pn.checkSign()){
    		//你应该自定义你的应用收到支付通知时的逻辑
//    		String channel			= pn.getChannel();
//    		String in_trade_no		= pn.getInTradeNo();
//    		String out_trade_no		= pn.getOutTradeNo();
//    		String title			= pn.getTitle();
//    		String total_fee		= pn.getTotalFee();
//    		String client_ip		= pn.getClientIp();
//    		String trade_status		= pn.getTradeStatus();
//    		String payok_ymdhis		= pn.getPayokYmdhis();
//    		String payed_ymdhis		= pn.getPayedYmdhis();
//    		String cancel_ymdhis	= pn.getCancelYmdhis();
//    		String random			= pn.getRandom();
//    		String sign				= pn.getSign();
    		Enumeration<String> params	= request.getParameterNames();
        	while(params.hasMoreElements()){
        		String name			= params.nextElement();
        		String value		= request.getParameterValues(name)[0];
        		result.append(name + ":" + value + "\n");
        	}
    	}    	
    	return result.toString();
    }
}
