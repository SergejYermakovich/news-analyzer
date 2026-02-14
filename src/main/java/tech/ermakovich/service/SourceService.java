package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ermakovich.model.entity.Source;
import tech.ermakovich.repository.SourceRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SourceService {
    private final SourceRepository sourceRepository;

    public List<Source> getAll() {
        return sourceRepository.findAll();
    }
}
