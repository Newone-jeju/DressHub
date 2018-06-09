package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LeaseInfoController {
    @Autowired
    LeaseInfoRepository leaseInfoRepository;


    @RequestMapping(value = "/leaseInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<LeaseInfo> getLeaseInfo(@RequestParam (defaultValue = "-1") Integer id,
                               @RequestParam (defaultValue = "-1") Integer product,
                               @RequestParam (defaultValue = "null")String user){

        List<LeaseInfo> leaseInfos = null;

        if(!id.equals(-1)) {
            leaseInfos = leaseInfoRepository.findById(id);
        }else if(!product.equals(-1) && !user.equals("null")){
            leaseInfos = leaseInfoRepository.findAllByLeaserAndProduct(user , product);
        }else if(!product.equals(-1)){
            leaseInfos = leaseInfoRepository.findAllByProduct(product);
        }else if(!user.equals("null")){
            leaseInfos = leaseInfoRepository.findAllByLeaser(user);
        }

        return leaseInfos;
    }

    @RequestMapping(value = "/leaseInfo/update/", method = RequestMethod.POST)
    public ResultMessage update(@ModelAttribute LeaseInfo leaseInfo){
        ResultMessage resultMessage = new ResultMessage();
        leaseInfoRepository.save(leaseInfo);
        resultMessage.setCode(200);
        resultMessage.setMessage("승인");

        return resultMessage;
    }

    @RequestMapping(value = "/leaseInfo/delete/", method = RequestMethod.POST)
    public ResultMessage delete(@RequestParam Integer leaseInfoId){
        ResultMessage resultMessage = new ResultMessage();
        leaseInfoRepository.deleteById(leaseInfoId);
        resultMessage.setCode(200);
        resultMessage.setMessage("승인");

        return resultMessage;
    }
}