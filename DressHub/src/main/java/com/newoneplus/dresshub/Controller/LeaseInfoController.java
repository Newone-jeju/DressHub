package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Service.LeaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class LeaseInfoController {
    @Autowired
    private LeaseInfoService leaseInfoService;


    @RequestMapping(value = "/leaseInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaseInfo(@RequestParam (defaultValue = "null") String id,
                               @RequestParam (defaultValue = "null") String product,
                               @RequestParam (defaultValue = "null")String user){

        String returnValue = null;
        if(!id.equals("null")) {
            HashMap leaseInfoHashMap = leaseInfoService.getLeaseInfoHashMapById(Integer.parseInt(id));
            returnValue = leaseInfoHashMap.toString();
        }else if(!product.equals("null") && !user.equals("null")){
            ArrayList leaseInfoHashMaps = leaseInfoService.getLeaseInfoHashMapsByUserAndProduct(user, Integer.parseInt(product));
            returnValue = leaseInfoHashMaps.toString();
        }else if(!product.equals("null")){
            ArrayList leaseInfoHashMaps = leaseInfoService.getLeaseInfoHashMapsByProduct(Integer.parseInt(product));
            returnValue = leaseInfoHashMaps.toString();
        }else if(!user.equals("null")){
            ArrayList leaseInfoHashMaps = leaseInfoService.getLeaseInfoHashsMapByUser(user);
            returnValue = leaseInfoHashMaps.toString();
        }
        return returnValue;
    }

    @RequestMapping(value = "/leaseInfo/update/", method = RequestMethod.POST)
    public ResultMessage update(@ModelAttribute LeaseInfo leaseInfo){
        ResultMessage resultMessage = new ResultMessage();
        if(leaseInfoService.askAuthority(leaseInfo.getId())){
            leaseInfoService.update(leaseInfo);
            resultMessage.setCode(200);
            resultMessage.setMessage("승인");
        }else{
            resultMessage.setCode(301);
            resultMessage.setMessage("권한이 없습니다.");
        }
        return resultMessage;
    }

    @RequestMapping(value = "/leaseInfo/delete/", method = RequestMethod.POST)
    public ResultMessage delete(@RequestParam Integer leaseInfoId){
        ResultMessage resultMessage = new ResultMessage();
        if(leaseInfoService.askAuthority(leaseInfoId)){
            leaseInfoService.delete(leaseInfoId);
            resultMessage.setCode(200);
            resultMessage.setMessage("승인");
        }else{
            resultMessage.setCode(301);
            resultMessage.setMessage("권한이 없습니다.");
        }

        return resultMessage;
    }
}