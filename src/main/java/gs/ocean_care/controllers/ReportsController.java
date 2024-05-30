package gs.ocean_care.controllers;

import gs.ocean_care.dtos.reports.RegisterReportDto;
import gs.ocean_care.dtos.reports.ReportsDto;
import gs.ocean_care.dtos.reports.UpdateReportDto;
import gs.ocean_care.services.ReportsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @PostMapping("/register/{userId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> register(@PathVariable Long userId, @RequestBody @Valid RegisterReportDto data) {
        reportsService.register(userId, data);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ReportsDto> update(@PathVariable Long id, @RequestBody @Valid UpdateReportDto data) {
        var updatedReport = reportsService.update(id, data);

        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        reportsService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<ReportsDto>> list(@PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<ReportsDto> reports = reportsService.findAll(pageable);

        return ResponseEntity.ok(reports);
    }

    @GetMapping("/list/{userId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<ReportsDto>> listByUser(@PathVariable Long userId, @PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<ReportsDto> reports = reportsService.findAllByUserId(userId, pageable);

        return ResponseEntity.ok(reports);
    }


}
