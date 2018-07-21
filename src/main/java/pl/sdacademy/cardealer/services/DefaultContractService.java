package pl.sdacademy.cardealer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Contract;
import pl.sdacademy.cardealer.repository.ContractRepository;

@Service
public class DefaultContractService implements ContractService {

    ContractRepository contractRepository;

    @Autowired
    public DefaultContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }
}
