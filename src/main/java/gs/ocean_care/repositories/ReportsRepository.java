package gs.ocean_care.repositories;

import gs.ocean_care.dtos.reports.ReportsDto;
import gs.ocean_care.models.Reports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Reports, Long> {

    Page<ReportsDto> findAllByActiveTrue(Pageable pageable);

    Page<ReportsDto> findAllByUserIdAndActiveTrue(Long userId, Pageable pageable);
}
