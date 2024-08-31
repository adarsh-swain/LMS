package com.lms.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.entity.LibraryBranch;
import com.lms.service.LibraryBranchService;

@Service
public class LibraryBranchServiceImpl implements LibraryBranchService {

    @Autowired
    private LibraryBranchRepository libraryBranchRepository;

    @Override
    public LibraryBranch saveLibraryBranch(LibraryBranch libraryBranch) {
        return libraryBranchRepository.save(libraryBranch);
    }

    @Override
    public LibraryBranch getLibraryBranchById(Long id) {
        return libraryBranchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Library branch not found with id: " + id));
    }

    @Override
    public List<LibraryBranch> getAllLibraryBranches() {
        return libraryBranchRepository.findAll();
    }

    @Override
    public void deleteLibraryBranch(Long id) {
        libraryBranchRepository.deleteById(id);
    }
    
}
