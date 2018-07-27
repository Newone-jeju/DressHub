package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ManagerNotice;
import com.newoneplus.dresshub.Repository.ManagerNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @PutMapping
    private void update(@RequestBody ManagerNotice managerNotice, HttpServletResponse res) throws IOException {
        if (managerNoticeRepository.existsById(managerNotice.getId())) {
            managerNoticeRepository.save(managerNotice);
        }
        else {
            res.sendError(404, "잘못된 접근입니다");
        }
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Integer id) {
        managerNoticeRepository.delete(managerNoticeRepository.findById(id).get());
    }

}
