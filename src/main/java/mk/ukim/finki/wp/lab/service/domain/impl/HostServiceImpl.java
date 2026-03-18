package mk.ukim.finki.wp.lab.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.exception.HostNotFoundException;
import mk.ukim.finki.wp.lab.repository.HostRepository;
import mk.ukim.finki.wp.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    @Override
    public Host findById(Long Id) {
        Optional<Host> host = hostRepository.findById(Id);

        if(host.isPresent()){
            return host.get();
        }else {
            throw new HostNotFoundException("lala");
        }
    }
}
