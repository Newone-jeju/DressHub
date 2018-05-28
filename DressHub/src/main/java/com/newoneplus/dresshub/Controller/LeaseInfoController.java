package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.LeaseInfo;
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
    public String update(@ModelAttribute LeaseInfo leaseInfo){
        if(leaseInfoService.askAuthority(leaseInfo.getId())){
            leaseInfoService.update(leaseInfo);
            return "redirect:/";
        }else{
            return "redirect:/errorpage/403.html";
        }
    }

    @RequestMapping(value = "/leaseInfo/delete/", method = RequestMethod.POST)
    public String delete(@RequestParam Integer leaseInfoId){
        if(leaseInfoService.askAuthority(leaseInfoId)){
            leaseInfoService.delete(leaseInfoId);
            return "redirect:/";
        }else{
            return "redirect:/errorpage/403.html";
        }
    }
}