package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.entity.LibraryBranch;
import com.lms.service.LibraryBranchService;

@RestController
@RequestMapping("/LMS")
public class LibraryBranchController {

    @Autowired
    private LibraryBranchService libraryBranchService;

    @GetMapping("/allbranch")
    public List<LibraryBranch> getAllLibraryBranches() {
        return libraryBranchService.getAllLibraryBranches();
    }

    @PostMapping("/savebranch")
    public LibraryBranch addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        return libraryBranchService.saveLibraryBranch(libraryBranch);
    }

    @GetMapping("/branchbyid/{id}")
    public LibraryBranch getLibraryBranchById(@PathVariable Long id) {
        return libraryBranchService.getLibraryBranchById(id);
    }

    @PutMapping("/updatebranch/{id}")
    public LibraryBranch updateLibraryBranch(@PathVariable Long id, @RequestBody LibraryBranch updatedLibraryBranch) {
        LibraryBranch libraryBranch = libraryBranchService.getLibraryBranchById(id);
        libraryBranch.setBranchName(updatedLibraryBranch.getBranchName());
        libraryBranch.setLocation(updatedLibraryBranch.getLocation());
        libraryBranch.setContactInformation(updatedLibraryBranch.getContactInformation());
        return libraryBranchService.saveLibraryBranch(libraryBranch);
    }

    @DeleteMapping("/deletebranch/{id}")
    public String deleteLibraryBranch(@PathVariable Long id) {
        libraryBranchService.deleteLibraryBranch(id);
        return "branch deleted Successfully";
    }
}