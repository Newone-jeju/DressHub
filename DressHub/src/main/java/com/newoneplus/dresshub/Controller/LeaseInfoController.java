package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "leaseInfo")
@ResponseBody
public class LeaseInfoController {
    @Autowired
    LeaseInfoRepository leaseInfoRepository;

    @GetMapping("/{id}")
    public LeaseInfo get(@PathVariable Integer id){
        return leaseInfoRepository.findById(id);
    }

    @GetMapping("/list/search")
    public List<LeaseInfo> getList(@RequestParam (defaultValue = "-1") Integer product,
                               @RequestParam (defaultValue = "null")String user){

        List<LeaseInfo> leaseInfos = null;

        if(!product.equals(-1) && !user.equals("null")){
            leaseInfos = leaseInfoRepository.findAllByLeaserAndProduct(user , product);
        }else if(!product.equals(-1)){
            leaseInfos = leaseInfoRepository.findAllByProduct(product);
        }else if(!user.equals("null")){
            leaseInfos = leaseInfoRepository.findAllByLeaser(user);
        }

        return leaseInfos;
    }


    @PostMapping
    public ResultMessage create(@ModelAttribute LeaseInfo leaseInfo, HttpServletResponse res){
        leaseInfoRepository.save(leaseInfo);
        res.setStatus(200);
        return null;
    }


    @PutMapping
    //TODO 권한검사
    public ResultMessage update(@ModelAttribute LeaseInfo leaseInfo, HttpServletResponse res){
        leaseInfoRepository.save(leaseInfo);
        res.setStatus(200);
        return null;
    }

    @DeleteMapping("/{id}")
    //TODO 권한검사
    public ResultMessage delete(@RequestParam Integer id, HttpServletResponse res){
        res.setStatus(200);
        leaseInfoRepository.deleteById(id);
        return null;
    }
}