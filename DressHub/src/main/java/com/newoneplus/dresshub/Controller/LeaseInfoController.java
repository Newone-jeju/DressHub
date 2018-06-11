package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.LeaseInfoLog;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.Repository.LeaseInfoLogRepository;
import com.newoneplus.dresshub.Repository.LeaseInfoRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "leaseInfo")
@ResponseBody
@AllArgsConstructor
public class LeaseInfoController {
    LeaseInfoRepository leaseInfoRepository;
    LeaseInfoLogRepository leaseInfoLogRepository;

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
    public ResultMessage create(@RequestBody LeaseInfo leaseInfo, HttpServletResponse res){
        //TODO tempcode must be deleted
        leaseInfo.setLeaser(AuthorizationService.getCurrentUser().getUid());
        leaseInfoRepository.save(leaseInfo);
        res.setStatus(200);
        return null;
    }


    @PutMapping
    //TODO 권한검사
    public ResultMessage update(@RequestBody LeaseInfo leaseInfo, HttpServletResponse res){
        //TODO tempcode must be deleted
        leaseInfo.setLeaser(AuthorizationService.getCurrentUser().getUid());
        leaseInfoRepository.save(leaseInfo);
        res.setStatus(200);
        return null;
    }

    @DeleteMapping("/{id}")
    //TODO 권한검사
    public ResultMessage delete(@PathVariable Integer id, HttpServletResponse res){
        res.setStatus(200);
        leaseInfoRepository.deleteById(id);
        return null;
    }

    @PostMapping("/leaseInfoLog")
    //TODO 권한검사
    public ResultMessage createLog(@RequestBody LeaseInfoLog leaseInfoLog, HttpServletResponse res){
        leaseInfoLogRepository.save(leaseInfoLog);
        res.setStatus(200);
        return null;
    }



}