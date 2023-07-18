package codesquad.todolist.travelers.process.service;

import static codesquad.todolist.travelers.global.ErrorCode.*;

import codesquad.todolist.travelers.global.CustomException;
import codesquad.todolist.travelers.global.ErrorCode;
import codesquad.todolist.travelers.process.domain.dto.ProcessRequestDto;
import codesquad.todolist.travelers.process.domain.entity.Process;
import codesquad.todolist.travelers.process.domain.repository.ProcessRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public void saveProcess(ProcessRequestDto processRequestDto) {
        Process process = ProcessRequestDto.toEntity(processRequestDto);
        processRepository.createProcess(process).orElseThrow(
                () -> new CustomException(FAIL_PROCESS_CREATE));
    }
}
