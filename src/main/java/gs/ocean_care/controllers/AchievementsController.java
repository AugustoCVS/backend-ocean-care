package gs.ocean_care.controllers;

import gs.ocean_care.dtos.achievements.AchievementsDto;
import gs.ocean_care.services.AchievementsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementsController {

    @Autowired
    private AchievementsService achievementsService;

    @GetMapping("/list/${userId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<AchievementsDto>> list(@PathVariable Long userId) {
        List<AchievementsDto> achievements = achievementsService.findAllByUserId(userId);

        return ResponseEntity.ok(achievements);
    }
}
