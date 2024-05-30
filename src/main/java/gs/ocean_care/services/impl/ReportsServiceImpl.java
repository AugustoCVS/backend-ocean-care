package gs.ocean_care.services.impl;

import gs.ocean_care.dtos.reports.RegisterReportDto;
import gs.ocean_care.dtos.reports.ReportsDto;
import gs.ocean_care.dtos.reports.UpdateReportDto;
import gs.ocean_care.models.Reports;
import gs.ocean_care.repositories.ReportsRepository;
import gs.ocean_care.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private ReportsRepository repository;

    @Override
    public Page<ReportsDto> findAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }

    @Override
    public Page<ReportsDto> findAllByUserId(Long userId, Pageable pageable) {
        return repository.findAllByUserIdAndActiveTrue(userId, pageable);
    }

    @Override
    public RegisterReportDto register(RegisterReportDto data){
        Reports report = new Reports(data);
        repository.save(report);

        return null;
    }

    @Override
    public ReportsDto update(Long id, UpdateReportDto data) {
        Reports report = repository.getReferenceById(id);
        report.update(data);

        return new ReportsDto(report);
    }

    @Override
    public void delete(Long id) {
        Reports report = repository.getReferenceById(id);
        report.softDelete();
    }

}
