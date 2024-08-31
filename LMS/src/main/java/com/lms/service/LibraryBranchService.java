package com.lms.service;

import java.util.List;

import com.lms.entity.LibraryBranch;

public interface LibraryBranchService {

	LibraryBranch saveLibraryBranch(LibraryBranch libraryBranch);

	LibraryBranch getLibraryBranchById(Long id);

	List<LibraryBranch> getAllLibraryBranches();

	void deleteLibraryBranch(Long id);

}
