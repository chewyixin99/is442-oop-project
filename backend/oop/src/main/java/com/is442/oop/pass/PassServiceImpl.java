package com.is442.oop.pass;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is442.oop.data.models.Pass;
import com.is442.oop.exception.ResourceNotFoundException;

@Service
public class PassServiceImpl implements PassService {
    @Autowired
    PassRepository passRepository;

    @Override
    public Pass getPass(Integer passId) throws ResourceNotFoundException {
        return passRepository.findById(passId).orElseThrow(() -> new ResourceNotFoundException("Pass", "Pass ID", passId));
    }

    @Override
    public List<Pass> getAllPasses() {
        return passRepository.findAll();
    }

    @Override
    public Pass createPass(PassRequest passRequest) {
        Pass pass = new Pass();
        pass.setPoi(passRequest.getPoi());
        pass.setPassDesc(passRequest.getPassDesc());
        pass.setNumGuests(passRequest.getNumGuests());
        pass.setReplacementFee(passRequest.getReplacementFee());
        pass.setPhysical(passRequest.getPhysical());
        pass.setPassStatus(passRequest.getPassStatus());
        pass.setDefunct(passRequest.getDefunct());
        passRepository.save(pass);
        return pass;
    }

    @Override
    public Pass updatePass(Integer passId, PassRequest passRequest) throws ResourceNotFoundException {
        Pass pass = null;
        Optional<Pass> queryPass = passRepository.findById(passId);
        if (queryPass.isEmpty()) {
            throw new ResourceNotFoundException("Pass", "Pass ID", passId);
        }
        pass = queryPass.get();
        pass.setPoi(passRequest.getPoi());
        pass.setPassDesc(passRequest.getPassDesc());
        pass.setNumGuests(passRequest.getNumGuests());
        pass.setReplacementFee(passRequest.getReplacementFee());
        pass.setPhysical(passRequest.getPhysical());
        pass.setPassStatus(passRequest.getPassStatus());
        passRepository.save(pass);
        return pass;
    }

    @Override
    public Pass deletePass(Integer passId) throws ResourceNotFoundException {
        Pass pass = null;
        Optional<Pass> queryPass = passRepository.findById(passId);
        if (queryPass.isEmpty()) {
            throw new ResourceNotFoundException("Pass", "Pass ID", passId);
        }
        pass = queryPass.get();
        pass.setDefunct(true);
        passRepository.save(pass);
        return pass;
    }
}