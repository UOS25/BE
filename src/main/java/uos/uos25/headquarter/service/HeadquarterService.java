package uos.uos25.headquarter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.entity.HeadQuarter;
import uos.uos25.headquarter.repository.HeadQuarterRepository;

@Service
@RequiredArgsConstructor
public class HeadquarterService {
    private final HeadQuarterRepository headQuarterRepository;

    public List<HeadQuarter> findAll() {
        return headQuarterRepository.findAll();
    }
}
