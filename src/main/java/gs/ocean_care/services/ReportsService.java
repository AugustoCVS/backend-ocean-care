package gs.ocean_care.services;

import gs.ocean_care.dtos.reports.RegisterReportDto;
import gs.ocean_care.dtos.reports.ReportsDto;
import gs.ocean_care.dtos.reports.UpdateReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportsService {

    public Page<ReportsDto> findAll(Pageable pageable);

    public Page<ReportsDto> findAllByUserId(Long userId, Pageable pageable);

    public RegisterReportDto register(Long userId, RegisterReportDto data);

    public ReportsDto update(Long id, UpdateReportDto data);

    public void delete(Long id);

}
