package uos.uos25.headQuarter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.headQuarter.entity.HeadQuarter;
import uos.uos25.headQuarter.repository.HeadQuarterRepository;

@Service
@RequiredArgsConstructor
public class HeadquarterService {
    private final HeadQuarterRepository headQuarterRepository;

    public List<HeadQuarter> findAll() {
        return headQuarterRepository.findAll();
    }
}
