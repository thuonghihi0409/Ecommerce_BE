package com.example.Hello.Controller;

import com.example.Hello.Service.UtilityService;
import com.example.Hello.dto.request.UtilityCreateRequest;
import com.example.Hello.entity.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilities")
public class UtilityController {
    @Autowired
    private UtilityService utilityService;
    // Tạo mới một utility
    @PostMapping
    public Utility createUtility(@RequestBody UtilityCreateRequest request) {
        return utilityService.createUtility(request);
    }
    // Lấy danh sách các utility
    @GetMapping
    public List<Utility> getUtilities() {
        return utilityService.getUtilities();
    }
    // Cập nhật utility
    @PutMapping("/{utilityId}")
    public Utility updateUtility(@PathVariable String utilityId, @RequestBody UtilityCreateRequest request) {
        return utilityService.updateUtility(utilityId, request);
    }
    // Xóa utility
    @DeleteMapping("/{utilityId}")
    public void deleteUtility(@PathVariable String utilityId) {
        utilityService.deleteUtility(utilityId);
    }
}
