package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ManagerNotice;
import com.newoneplus.dresshub.Repository.ManagerNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class ManagerNoticeController {
    private final ManagerNoticeRepository managerNoticeRepository;

    @GetMapping("/{id}")
    private ManagerNotice get(@PathVariable Integer id){
        return managerNoticeRepository.findById(id).get();
    }

    @GetMapping("/list")
    private List<ManagerNotice> getList(){
        return managerNoticeRepository.findAll();
    }

    @PostMapping
    private ManagerNotice create(@RequestBody ManagerNotice managerNotice) {
        return managerNoticeRepository.save(managerNotice);
    }

}
